// monsterConnect.js

// Wait for the DOM to load
document.addEventListener('DOMContentLoaded', () => {
    const submitButton = document.getElementById('SubmitButton');

    // Add an event listener for the click event
    submitButton.addEventListener('click', () => {
        onSubmitButtonClick(); // Call your function when the button is clicked
    });
});

// Function to handle the button click
function onSubmitButtonClick() {
    const nameToSend = "Nigro"; // The word you want to send

    // Send the word to the Java backend
    fetch('http://localhost:8080/hello', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain', // Set content type to plain text
        },
        body: nameToSend,// Send the plain string directly
    })

}
