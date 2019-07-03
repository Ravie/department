function findBySalary() {
    let minSalary = parseFloat(document.getElementById("min").value);
    let maxSalary = parseFloat(document.getElementById("max").value);
    if (minSalary > maxSalary) {
        swal({
            title: "Ошибка ввода!",
            text: "Минимальное значение больше максимального",
            icon: "warning",
        });
        return;
    } else {
        document.getElementById("findBySalary").setAttribute("type", "submit");
    }
}

window.onload = function () {
    document.getElementById("findBySalary").addEventListener("click", findBySalary);
};