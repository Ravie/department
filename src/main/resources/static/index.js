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