const $form = document.querySelector('.form');
const $input = document.querySelector('.serach-input');
const $textArea = document.querySelector('.search-text');
const $copyBtn = document.querySelector('.text-copy');

$form.onsubmit = (e) => {
  e.preventDefault();
  const URL = '/url-convert';

  fetch(URL, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({originUrl: $input.value}),
  }).then((response) => {
    if (!response.ok) {
      throw response;
    }
    return response.json()
  }).then((url) => {
    $copyBtn.style.display = 'block';
    $textArea.innerHTML = url.shortUrl;
    $textArea.style.color = 'rgb(165, 165, 165)';
  }).catch(exception => {
    exception.text().then(message => {
      $textArea.innerHTML = message;
      $copyBtn.style.display = 'none';
      $textArea.style.color = 'red';
    })
  });
};


$copyBtn.onclick = () => {
  const tempElem = document.createElement('textarea');
  tempElem.value = $textArea.innerHTML;
  document.body.appendChild(tempElem);

  tempElem.select();
  document.execCommand('copy');
  document.body.removeChild(tempElem);
};