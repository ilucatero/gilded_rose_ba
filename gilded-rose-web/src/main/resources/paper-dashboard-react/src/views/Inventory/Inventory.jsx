import React from "react";
import { Card, CardBody, CardHeader, CardTitle, Row, Col } from "reactstrap"; // bootstrap components for ReactJs

import $ from "jquery/dist/jquery";
import dataTable from "datatables.net/js/jquery.dataTables";

// bind Datatable object to jquery so it can use jquery inner bindings
$.DataTable = dataTable;

// TODO: migrate it to react-tables => https://www.npmjs.com/package/react-table

class InventoryTable extends React.Component {
  constructor(props) {
    super(props);
    this.refInvDatatable = React.createRef();
    this.state = { dataSet: [], error: null };
  }

  componentDidMount(prevProps) {
      this.fetchItems()
          .then(dataResp => {
            this.setState({ dataSet: dataResp});
            return dataResp;
           }).then(dataResp => {
                console.log("loading");
                let table = $(this.refInvDatatable.current).DataTable({
                  data: dataResp,
                  columns: [
                    { data: "id", visible: false },
                    { data: "name", title: "Name" },
                    { data: "sellIn", title: "Sell In"  },
                    { data: "type", title: "Type"  },
                    { data: "quality", title: "Quality"  },
                    { data: "tags", title: "Tags", "defaultContent": ""  },
                    { // Actions column
                      "className":      'item-actions',
                      "orderable":      false,
                      "data":           null,
                      "title":          "Actions",
                      "defaultContent": '<a href="#" class="btn btn-outline-success btn-sm item-degrade" role="button" aria-pressed="true">Degrade</a>'
                    },
                  ]
                });

                let _this = this;
                $('td.item-actions')
                      .on('click', '.item-degrade', function () {
                        let tr = $(this).closest('tr');
                        let row = table.row( tr );
                        console.debug("Updating data:", row.data());
                        let itemId = row.data().id;

                          _this.degradeItem(itemId, row);
                      } );

           }).catch(error => this.setState({ error }) );

  }

  fetchItems(){
      return fetch("/items/get-items")
          .then(response => {
              if (response.ok) {
                  return response.json();
              } else {
                  throw new Error('Something went wrong ...');
              }
          })
  }

  degradeItem(id, row){
        fetch("/items/degrade/"+id,{
            method: "PATCH",
            headers: {
                'accept': 'application/json'
            },
        }).then(response => {
            if (response.ok) {
                response.json();
            } else {
                throw new Error('Something went wrong ...');
            }
        }).then(response => {
            console.log("Item " + id + " has been degraded.");
            // if success update dataTable data values with item's data
            this.reloadItemInDatatable(id, row);
        }).catch(error => {
            console.log( "Could not update item with id " + id + ". Error:" + error);
            throw error;
        });

  }

  reloadItemInDatatable(id, row){
        fetch("/items/"+id)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Something went wrong ...');
                }
            }).then(dataSet => {
                console.debug("Updating dataSet: ",row.data(),"  with:", dataSet[0]);
                dataSet[0].tags = row.data().tags;
                dataSet[0].name = row.data().name + '*';
                row.data(dataSet[0]);
            }).catch(error => {
                console.log( "Could not update item with id " + id + ". Error:" + error);
                throw error;
            });
  }


    render() {
        const { error } = this.state;

        let cardContent =  <table ref={this.refInvDatatable} className="display inventory-datatable" />;
        if (error) {
          cardContent = <p>{error.message}</p>;
        }

        return (
            <div className="content">
              <Row>
                <Col xs={12}>
                  <Card>
                    <CardHeader>
                      <CardTitle tag="h4">Simple Table</CardTitle>
                    </CardHeader>
                    <CardBody>
                      { cardContent }
                    </CardBody>
                  </Card>
                </Col>
              </Row>
            </div>
        );
  }
}


export default InventoryTable ;
