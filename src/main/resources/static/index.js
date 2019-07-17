function minSalaryLessThanMax() {
    let minSalary = parseFloat(document.getElementById("min").value);
    let maxSalary = parseFloat(document.getElementById("max").value);
    if (minSalary > maxSalary) {
        Swal.fire({
            title: "Ошибка ввода!",
            text: "Минимальная зарплата больше максимальной",
            type: "warning",
            backdrop: `rgba(0,0,123,0.4) `
        });
        return false;
    }
    return true;
}

function checkUserForm() {
    if (document.getElementById("userId").value == document.getElementById("curUserId").value) {
        return confirm("Вы изменили настройки своего аккаунта. Будет произведен логаут. Продолжить?");
        // sweetalert2 uses promises and didn't block submit form
        /*swal(await {
            title: 'Продолжить?',
                text: "Вы изменили настройки своего аккаунта. Будет произведен логаут.",
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes'
        }).then(function (isConfirm) {
            return isConfirm;
        });*/
    }
    return true;
}
