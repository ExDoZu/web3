form.addEventListener('click', function (event) {
        if (event.target.type === 'checkbox') {
            let checkboxes = document.getElementsByClassName("X-input");
            for (let i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    checkboxes[i].checked = false;
                }
            }
            event.target.checked = true;
        }
    }
)
