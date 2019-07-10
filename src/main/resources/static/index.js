function minSalaryLessThanMax() {
    let minSalary = parseFloat(document.getElementById("min").value);
    let maxSalary = parseFloat(document.getElementById("max").value);
    if (minSalary > maxSalary) {
        Swal.fire({
            title: "Ошибка ввода!",
            text: "Минимальное значение больше максимального",
            type: "warning",
            backdrop:
                `
                rgba(0,0,123,0.4)
                url("https://sweetalert2.github.io/images/nyan-cat.gif")
                center left
                no-repeat
                `
        });
        return false;
    }
    return true;
}