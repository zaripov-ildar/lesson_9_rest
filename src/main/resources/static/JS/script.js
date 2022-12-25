angular.module('store', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8081/store/products';
    let totalPages = 0;
    let pageNum = 1;
    $scope.min_price = null;
    $scope.max_price = null;
    $scope.title_part = null;
    $scope.newTitle = null;
    $scope.newPrice = null;

    $scope.product = {
        id: null,
        title: null,
        price: null
    }


    $scope.showButtonLayer = function () {
        let btn_layer = document.querySelector(".btn_layer");
        let buttonLayer = ``;
        for (let i = 1; i <= totalPages; i++) {
            if (pageNum === i) {
                buttonLayer += `<button class="btn-danger pb">${i}</button>`
            } else {
                buttonLayer += `<button  class="btn-primary pb">${i}</button>`
            }
        }
        btn_layer.innerHTML = buttonLayer;
    };


    $scope.loadProducts = function () {
        $http({
            url: contextPath,
            method: "GET",
            params: {
                min_price: $scope.min_price,
                max_price: $scope.max_price,
                title_part: $scope.title_part,
                page: pageNum
            }
        }).then(function (response) {
            console.log(response);
            $scope.productList = response.data["content"];
            totalPages = response.data["totalPages"];
            console.log($scope.productList);
            $scope.showButtonLayer();
        })
    }

    $scope.deleteProduct = function (productId) {
        $http({
            url: contextPath + "/" + productId,
            method: "DELETE"
        }).then(function () {
            $scope.loadProducts();
        })
    };


    $scope.add = function () {
        $http({
            url: contextPath,
            method: "POST",
            data: {
                title: $scope.newTitle,
                price: $scope.newPrice
            }
        }).then(function () {
            $scope.newPrice = null;
            $scope.newTitle = null;
            $scope.loadProducts();
        });
    };

    $scope.update = function () {
        $http({
            url: contextPath,
            method: "PUT",
            contentType: 'application/json; charset=utf-8',
            data: $scope.product
        }).then(function () {
            $scope.product.id = null;
            $scope.product.title = null;
            $scope.product.price = null;
            $scope.loadProducts();
        })
    }

    $scope.findById = function () {
        console.log($scope.product.id);
        $http({
            url: contextPath + "/" + $scope.product.id,
            method: "GET"
        }).then(function (response) {
            $scope.product.title = response.data["title"];
            $scope.product.price = response.data["price"];
            $scope.product.id = response.data["id"];
        })
    };


    $scope.loadProducts();
    document.addEventListener('click', function (event) {
        if ([...event.target.classList].includes("pb")) {
            pageNum = Number(event.target.textContent);
            $scope.loadProducts();
        }
    });
})