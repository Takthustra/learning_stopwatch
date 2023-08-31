const stopwatch = document.getElementById('stopwatch');
const startButton = document.getElementById('start');
const stopButton = document.getElementById('stop');
const resetButton = document.getElementById('reset');
const saveButton = document.getElementById('save');

//stoptimeの保存用スペース
let cur;
// 開始時間
let startTime;
// 停止時間
let stopTime = 0;
// タイムアウトID
let timeoutID;

// ページが読み込まれた時に実行
window.addEventListener('load',() => {
	
	cur = document.querySelector("input[name='stopTime']");
	
	if(cur.value !== ""){
		
	stopTime = Number(cur.value);
	const currentTime = new Date(stopTime);
	 
	const h = String(currentTime.getUTCHours()).padStart(2, '0');
  	const m = String(currentTime.getUTCMinutes()).padStart(2, '0');
  	const s = String(currentTime.getUTCSeconds()).padStart(2, '0');

  	stopwatch.textContent = `${h}:${m}:${s}`;
  	document.querySelector("input[name='time']").value = `${h}:${m}:${s}`;
	}
	
});


// 時間を表示する関数
function displayTime() {
  const currentTime = new Date(Date.now() - startTime + stopTime);
 
  const h = String(currentTime.getUTCHours()).padStart(2, '0');
  const m = String(currentTime.getUTCMinutes()).padStart(2, '0');
  const s = String(currentTime.getUTCSeconds()).padStart(2, '0');
  
  //tst  
  cur.value = Date.now() - startTime + stopTime;

  stopwatch.textContent = `${h}:${m}:${s}`;
  document.querySelector("input[name='time']").value = `${h}:${m}:${s}`;
  
  timeoutID = setTimeout(displayTime, 10);
}

// スタートボタンがクリックされたら時間を進める
startButton.addEventListener('click', () => {
  startButton.disabled = true;
  stopButton.disabled = false;
  resetButton.disabled = true;
  
  //更新前のstoptime取得
  if(cur.value !== ""){
	  stopTime = Number(cur.value);
	  cur.value = "";
  }
  
  startTime = Date.now();
  
  displayTime();
});

// ストップボタンがクリックされたら時間を止める
stopButton.addEventListener('click', function() {
  startButton.disabled = false;
  stopButton.disabled = true;
  resetButton.disabled = false;
  clearTimeout(timeoutID);
  stopTime += (Date.now() - startTime);
 
  //stoptimeの保存用スペースに値を入れる
  cur.value = stopTime;
});

// リセットボタンがクリックされたら時間を0に戻す
resetButton.addEventListener('click', function() {
  startButton.disabled = false;
  stopButton.disabled = true;
  resetButton.disabled = true;
  stopwatch.textContent = '00:00:00';
  stopTime = 0;
  cur.value = 0;
});