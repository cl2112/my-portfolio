/** Adds an onclick event listener to the get hello button. */
const getHelloButton = document.getElementById('get-hello-button');
document.addEventListener('click', showHello);

/** Fetches a hello from the server and adds it to the page. */ 
async function showHello() {
  const responseFromServer = await fetch('/hello');
  const textFromResponse = await responseFromServer.text();

  const getHelloContainer = document.getElementById('get-hello-container');
  getHelloContainer.innerHTML = textFromResponse;
}
