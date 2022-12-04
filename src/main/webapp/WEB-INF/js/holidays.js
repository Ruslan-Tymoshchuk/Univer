/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        height: '600px',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },
        eventSources: [
            {
                url: '/univer/api/v1/holidays/',
                color: 'yellow',
                textColor: 'black'
            }
        ]
    });
    calendar.render();
});