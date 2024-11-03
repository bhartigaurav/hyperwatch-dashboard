    document.getElementById("pieChart").innerText = '';
    function renderPieChart(totalAvailable, active)
{
	const inactive = totalAvailable-active;
	var xValues = ["Inactive", "Active"];
var yValues = [inactive,active];
var barColors = [
  "#b91d47",
  "#1e7145"
];

new Chart("pieChart", {
  type: "pie",
  data: {
    labels: xValues,
    datasets: [{
      backgroundColor: barColors,
      data: yValues
    }]
  }
});
}