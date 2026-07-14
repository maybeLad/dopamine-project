/**
 * 
 */

function startCountdown(targetDate, elementId) {
    const el = document.getElementById(elementId);
    if (!el) return;

    const interval = setInterval(() => {
        const now = new Date().getTime();
        const distance = targetDate - now;

        if (distance <= 0) {
            clearInterval(interval);
            el.textContent = "Disponibile ora!";
            return;
        }

        const days = Math.floor(distance / (1000 * 60 * 60 * 24));
        const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        const seconds = Math.floor((distance % (1000 * 60)) / 1000);

        el.textContent = `${days}g ${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;

    }, 1000);
}

document.addEventListener('DOMContentLoaded', () => {
    const targetDate1 = new Date('2026-09-10T00:00:00').getTime();
    const targetDate2 = new Date('2026-09-20T00:00:00').getTime();

    startCountdown(targetDate1, 'countdown1');
    startCountdown(targetDate2, 'countdown2');
});