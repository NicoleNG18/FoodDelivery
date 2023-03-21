let loadTest = document.getElementById('orderButton');

loadTest.addEventListener('click', onLoadTest);

function onLoadTest() {
    let id = document.getElementById('orderId').getAttribute('value');

    let testCtrl = document.getElementById('orderTableBody');
    testCtrl.innerHTML = '';

    fetch(`http://localhost:8081/api/order/details/${id}`)
        .then(response => response.json())
        .then(order => {


            let row = document.createElement('tr');

            let idCol = document.createElement('td');
            let clientCol = document.createElement('td');
            let priceCol = document.createElement('td');
            let statusCol = document.createElement('td');
            let addressCol = document.createElement('td');
            let contactNumberCol = document.createElement('td');

            idCol.textContent = id;
            clientCol.textContent = order.client;
            priceCol.textContent = order.price;
            statusCol.textContent = order.status;
            addressCol.textContent = order.address;
            contactNumberCol.textContent = order.contactNumber;

            row.appendChild(idCol);
            row.appendChild(clientCol);
            row.appendChild(priceCol);
            row.appendChild(statusCol);
            row.appendChild(addressCol);
            row.appendChild(contactNumberCol);

            testCtrl.appendChild(row);
        })

}