(function() {

	"use strict";

    $.get({
        url: contextPath + '/ajax/new-user',
        dataType: "json",
        success: function(res) {
        	
        	var userTable = $('#userTable');
        	
            if (res.length == 0) {
            	userTable.hide();
                $('.tableEmptyMessage1').show();
            } else {
                $.each(res, function(key, value) {
                	userTable.append(dashboardUserRow(value));
                });

            }

        },
        error: function(error) {
            console.log(error);
            alert("There's a problem fetching new users.");
        }

    })

    $.get({
        url: contextPath + '/ajax/inventory-item/low-stock',
        dataType: "json",
        success: function(res) {
            if (res.length == 0) {
            	
            	var inventoryItemTable = $('#inventoryItemTable');
            	
            	inventoryItemTable.hide();
                $('.tableEmptyMessage2').show();
            } else {
                $.each(res, function(key, value) {
                	inventoryItemTable.append(dashboardProductRow(value));
                });
            }
        },
        error: function(error) {
            console.log(error);
            alert("There's a problem fetching low stock products.");
        }

    })

})();