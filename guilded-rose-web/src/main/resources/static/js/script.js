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
                    { data: "name", title: "Name" },
                    { data: "sellIn", title: "Sell In"  },
                    { data: "quality", title: "Quality"  },

                ]
            });

        } catch (e) {
            console.error(e);
        }
    });

    request.fail(function( jqXHR, textStatus ) {
        console.log( "Request failed: ", textStatus );
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