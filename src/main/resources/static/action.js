const $form = document.querySelector('.form');
const $input = document.querySelector('.serach-input');
const $submitBtn = document.querySelector('.serach-button');
const $textArea = document.querySelector('.search-text');
const $copyBtn = document.querySelector('.text-copy');
const $errorText = document.querySelector('.error-text');

$form.onsubmit = (e) => {
  e.preventDefault();
  const URL = '/url-convert';

  fetch(URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ originUrl: $input.value }),
  })
  .then((response) => response.json())
  .then((url) => {
    if (!url.shortUrl) {
      $copyBtn.style.display = 'none';
      $textArea.innerHTML = 'http:// 또는 https://를 포함한 URL을 입력해주세요!';
      $textArea.style.color = 'red';
    } else if (url.shortUrl) {
      $copyBtn.style.display = 'block';
      $textArea.innerHTML = url.shortUrl;
      $textArea.style.color = 'rgb(165, 165, 165)';
    }
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
