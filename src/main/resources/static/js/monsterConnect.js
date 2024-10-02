// Assuming this JavaScript is attached to the Vaadin view properly

function callPrintName() {
    // Access the Vaadin component and invoke the server-side method
    const vaadinElement = document.querySelector('vaadin-vertical-layout');

    if (vaadinElement && vaadinElement.$server) {
        vaadinElement.$server.printName("Nigro");
    } else {
        console.error("Server object or Vaadin element not found!");
    }
}

SubmitButton.onclick = function() {
    // Call the printName method in WebPageOpener
    callPrintName();

    console.log("I am working");
}
