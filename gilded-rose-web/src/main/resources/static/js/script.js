function loadItems(){
    var request = $.ajax({
        url: "/items/get-items",
        method: "GET",
        dataType: "json",
    });

    request.done(function( dataSet ) {
        try{
            var table = $('#inventory').DataTable({
                data: dataSet,
                columns: [
                    { data: "id", visible: false },
                    { data: "name", title: "Name" },
                    { data: "sellIn", title: "Sell In"  },
                    { data: "quality", title: "Quality"  },
                    { data: "tag", title: "Tags", "defaultContent": ""  },
                    { // Actions column
                        "className":      'item-actions',
                        "orderable":      false,
                        "data":           null,
                        "title":          "Actions",
                        "defaultContent": '<a class="button ok item-degrade">Degrade</a>'
                    },
                ]
            });

            $('#inventory tbody')
                .on('click', 'td.item-actions .item-degrade', function () {
                    var tr = $(this).closest('tr');
                    var row = table.row( tr );
                    console.debug("Updating data:", row.data());
                    var itemId = row.data().id;
                    // TODO: call ms to degrade the selected item id
                    // TODO: if success update dataTable data values for quality/sellIn
                    degradeItem(itemId, table);
                } );
        } catch (e) {
            console.error(e);
        }
    });

    request.fail(function( jqXHR, textStatus ) {
        console.log( "Request failed: ", textStatus );
    });
}

function degradeItem(id, table){
    var request = $.ajax({
        url: "/items/degrade/"+id,
        method: "PATCH",
        dataType: "json",
    });

    request.done(function( response ) {
        alert("Item " + id + " has been updated. update page to see changes");

        // TODO: use the response to reload data of this row
    });

    request.fail(function( jqXHR, textStatus ) {
        alert( "Could not update item with id " + id + ". Error:" + textStatus);
    });
}

function setFooterCopyright(text){
    $('#footer #copyright').html(text);
}

// jQuery Initialize
$(document).ready( function () {
    loadItems();
    setFooterCopyright( '&copy;' + new Date().getFullYear() + ', Gilded Rose.')
});