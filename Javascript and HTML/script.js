
const timerDisplay = document.getElementById('timerDisplay');
const startBtn = document.getElementById('startBtn');
const pauseBtn = document.getElementById('pauseBtn');
const resetBtn = document.getElementById('resetBtn');
const customMinutesInput = document.getElementById('customMinutes');
const setTimerBtn = document.getElementById('setTimerBtn');
const sessionCountDisplay = document.getElementById('sessionCount');


let sessionCount = 0;
let running = false;
let totalSeconds = 1*60;
let remainingSeconds = totalSeconds;
let defaultTime = totalSeconds;
let setMinutes = 0;
let timerInterval = null;

updateDisplay();

startBtn.addEventListener('click', startTimer);
pauseBtn.addEventListener('click', pauseTimer);
resetBtn.addEventListener('click', resetTimer);
setTimerBtn.addEventListener('click', setCustomTimer);

function startTimer() {
    if(!running){
        running = true;
        timerInterval = setInterval(()=>{
            if(remainingSeconds>0){
                remainingSeconds--;
                updateDisplay();
            }else{
                completeSession();
            }
        }, 1000)
    }
}

function pauseTimer() {
    if(running){
        running = false;
        clearInterval(timerInterval);
        timerInterval = null;
    }else if(!running && remainingSeconds!=defaultTime){
        startTimer();
    }
}


function resetTimer() {
        running = false;
        clearInterval(timerInterval)
        timerInterval = null;;
        remainingSeconds = defaultTime;
        updateDisplay();
}


function setCustomTimer() {
    const minutes = parseInt(customMinutesInput.value);

    if (isNaN(minutes) || minutes < 1 || minutes > 60) {
        alert('Please enter a number between 1 and 60');
        customMinutesInput.value = '';
        return;
    }
    
    pauseTimer();

    remainingSeconds = minutes * 60;
    defaultTime = remainingSeconds;
    updateDisplay();
    
}


function updateDisplay() {
    const minutes = Math.floor(remainingSeconds/60);
    const seconds = Math.floor(remainingSeconds%60);

    const formattedMinutes = String(minutes).padStart(2, '0');
    const formattedSeconds = String(seconds).padStart(2, '0');

    timerDisplay.textContent = `${formattedMinutes}:${formattedSeconds}`
}


function completeSession() {
    pauseTimer();
    sessionCount++;

    sessionCountDisplay.textContent = sessionCount;
    remainingSeconds = defaultTime;
    alert('Session Completed')
    updateDisplay();
}

document.addEventListener('keydown', (e) => {
    if (e.key === ' ' || e.key === 'Spacebar') {
        e.preventDefault();
        if (isRunning) {
            pauseTimer();
        } else {
            startTimer();
        }
    } else if (e.key === 'r' || e.key === 'R') {
        resetTimer();
    }
});
