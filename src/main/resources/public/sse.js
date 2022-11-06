const evtSource = new EventSource("/notifications/v2");

evtSource.onmessage = function (e) {
    const newElement = document.createElement("li");
    const eventList = document.getElementById('list');
    console.log(e.data);
    newElement.innerHTML = "message: " + e.data;
    eventList.appendChild(newElement);
};