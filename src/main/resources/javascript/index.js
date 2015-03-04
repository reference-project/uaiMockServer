var app = angular.module('uaiMockServerApp', ['tableSort', 'angular-growl', 'ngAnimate']);

app.config(['growlProvider', function(growlProvider) {
    growlProvider.globalTimeToLive(2000);
}]);

app.controller('routeController', function($scope, $http, growl) {
    $scope.items = [{name: 'one', age: 30 },{ name: 'two', age: 27 },{ name: 'three', age: 50 }];
    $scope.loadTable = function(){
        $http.get('/uai-mock-server-gui/uaiRoute').
            success(function(data) {
                $scope.routeRowList = [];
                $scope.basicConfiguration = data.basicConfiguration;
                $scope.httpMethodArray = data.httpMethodArray;

                for (var i = 0; i < data.routeList.length; i++) {
                    var routeRow = {};

                    routeRow.requestName = data.routeList[i].request.name;
                    routeRow.requestMethod = data.routeList[i].request.method;
                    routeRow.requestPath = data.routeList[i].request.path;
                    routeRow.responseCode = data.routeList[i].response.statusCode;
                    routeRow.responseContentType = data.routeList[i].response.contentType;

                    routeRow.route = data.routeList[i];

                    $scope.routeRowList.push(routeRow);
                }
            }
        );
    }

    $scope.loadTable();

    $scope.hasErrorInList = function(type , listToCheck, error) {
        if (listToCheck == null) {
            return false;
        }

        for (var i = 0; i < listToCheck.length; i++) {
            var list = listToCheck[i];
            if (list.name == "" && list.valueList != null && list.valueList.length > 0) {
                error.hasError = true;
                error.errorHtml += "<li>There is a value of " + type + " created, but has not name: " + list.valueList +" </li>";
            }

            if ((list.name != null && list.name.trim() != "") && (list.valueList == null || list.valueList.length == 0)) {
                error.hasError = true;
                error.errorHtml += "<li>There is a value of " + type + " with the name: " + list.name + " but it has no value</li>";
            }
        }
    }

    $scope.getErrorText = function(route) {
        var error = {};
        error.hasError = false;
        error.errorHtml = "<ul>";

        if (route.request.name == null || route.request.name.trim() == "") {
            error.hasError = true;
            error.errorHtml += "<li>request.name was not found</li>";
        }

        if (route.request.path == null || route.request.path.trim() == "") {
            error.hasError = true;
            error.errorHtml += "<li>request.path was not found</li>";
        }

        if (route.request.method == null || route.request.method.trim() == "") {
            error.hasError = true;
            error.errorHtml += "<li>request.method was not found</li>";
        }

        if (route.response.statusCode == null || route.response.statusCode <= 0) {
            error.hasError = true;
            error.errorHtml += "<li>response.statusCode was not found</li>";
        }

        if (route.response.contentType == null || route.response.contentType.trim() == "") {
            error.hasError = true;
            error.errorHtml += "<li>response.contentType was not found</li>";
        }

        $scope.hasErrorInList("Request ---> HeaderList", route.request.requiredHeaderList, error);
        $scope.hasErrorInList("Request ---> QueryParamList", route.request.requiredQueryParamList, error);

        $scope.hasErrorInList("Response ---> HeaderList", route.response.headerList, error);

        if (error.hasError) {
            error.errorHtml += "</ul>";
            return error.errorHtml;
        }

        return null;
    }

    $scope.saveRoute = function() {
        $scope.selectedRouteRow.route.request.requiredHeaderList = $scope.convertStringToList($scope.selectedRouteRow.route.request.requiredHeaderList);
        $scope.selectedRouteRow.route.request.requiredQueryParamList = $scope.convertStringToList($scope.selectedRouteRow.route.request.requiredQueryParamList);
        $scope.selectedRouteRow.route.response.headerList = $scope.convertStringToList($scope.selectedRouteRow.route.response.headerList);

        var errorText = $scope.getErrorText($scope.selectedRouteRow.route);

        if (errorText != null) {
            $scope.hasValidationErrors = true;
            $('#errorDiv').html(errorText);
            return;
        }

        if ($scope.action === 'update') {
            $http.put('/uai-mock-server-gui/uaiRoute', $scope.selectedRouteRow.route).
                success(function(data) {
                    $scope.loadTable();
                    $('#routeModal').modal('toggle');
                    $scope.displaySuccessGrowl();
                }
            ).error(function(){
                    $scope.displayErrorGrowl();
                });
        } else {
            $http.post('/uai-mock-server-gui/uaiRoute', $scope.selectedRouteRow.route).
                success(function(data) {
                    $scope.loadTable();
                    $('#routeModal').modal('toggle');
                    $scope.displaySuccessGrowl();
                }
            ).error(function(){
                    $scope.displayErrorGrowl();
                });
        }

    }

    $scope.displaySuccessGrowl = function() {
        growl.addSuccessMessage("Operation Confirmed (:");
    }

    $scope.displayErrorGrowl = function() {
        growl.addErrorMessage("Something went wrong :(");
    }

    $scope.convertStringToList = function(requiredHeaderList) {
        if (requiredHeaderList == null) {
            return;
        }

        var listTrimmed = [];

        for (var i = 0; i < requiredHeaderList.length; i++) {
            if (requiredHeaderList[i].name != null && requiredHeaderList[i].name.trim() == "") {
                if (requiredHeaderList[i].valueList == null || requiredHeaderList[i].valueList.length == 0) {
                    continue;
                }
            }

            if (typeof requiredHeaderList[i].valueList === "string") {
                requiredHeaderList[i].valueList = requiredHeaderList[i].valueList.split(",");
            }

            listTrimmed.push(requiredHeaderList[i]);
        }

        return listTrimmed;
    }

    $scope.displaySelected = function (routeRow) {
        $scope.hasValidationErrors = false;
        $scope.action = 'update';

        $scope.selectedRouteRow = {};
        angular.copy(routeRow, $scope.selectedRouteRow);

        $('#myTab a:first').tab('show')
        $('#routeModal').modal('toggle');
    }

    $scope.displayCreateNew = function () {
        $scope.action = 'create';
        $scope.hasValidationErrors = false;
        $scope.selectedRouteRow = {};
        $scope.selectedRouteRow.route = {};
        $scope.selectedRouteRow.route.request = {};
        $scope.selectedRouteRow.route.response = {};
        $scope.selectedRouteRow.route.response.contentType = $scope.basicConfiguration.defaultContentType;
        $scope.selectedRouteRow.route.uaiFile = {};
        $scope.selectedRouteRow.route.uaiFile.fullPath = $scope.basicConfiguration.uaiMainFile.fullPath;

        $('#myTab a:first').tab('show')
        $('#routeModal').modal('toggle');
    }

    $scope.deleteSelectedConfirmation = function () {
        $scope.action = 'delete';
        $('#routeModal').modal('toggle');
        $('#deleteRouteModal').modal('toggle');
    }

    $scope.cancelDelete = function () {
        $('#deleteRouteModal').modal('toggle');
        $('#routeModal').modal('toggle');
    }

    $scope.addNewHeaderRequest = function (request) {
        if (request.requiredHeaderList == null) {
            request.requiredHeaderList = [];
        }

        request.requiredHeaderList.push({name:"", valueList: []});
    }

    $scope.addNewHeaderResponse = function (response) {
        if (response.headerList == null) {
            response.headerList = [];
        }

        response.headerList.push({name:"", valueList: []});
    }

    $scope.addNewQueryParamRequest = function (request) {
        if (request.requiredQueryParamList == null) {
            request.requiredQueryParamList = [];
        }

        request.requiredQueryParamList.push({name:"", valueList: []});
    }

    $scope.delete = function () {
        $http.delete('/uai-mock-server-gui/uaiRoute?routeId='+$scope.selectedRouteRow.route.id).
            success(function(data) {
                $scope.loadTable();
                $('#deleteRouteModal').modal('toggle');
                $scope.displaySuccessGrowl();
            }
        ).error(function(){
                $scope.displayErrorGrowl();
            });
    }
});