async function changeInstance(element) {
    const instanceId = element.id;
    let url;
    // console.log("instance id to select is "+instanceId);
    // Determine URL based on instance ID
    switch (instanceId) {
		case 'all':
            url = 'https://api/all-instance-data';
            break;
        case 'dev':
            url = 'https://api/dev-instance-data';
            break;
        case 'stage':
            url = 'https://api/stage-instance-data';
            break;
        case 'prod':
            url = 'https://api/prod-instance-data';
            break;
        default:
            console.error("api/all", instanceId);
            return;
    }

    // Fetch data from the URL
    try {
       // const response = await fetch(url);
        // const data = await response.json();
		 const data={
    "status": "success",
    "message": "Data fetched successfully",
    "totalAvailable": 5,
    "active": 4,
    "instances": [
        {
            "id": "instance1",
            "application": "App1",
            "cpuConsumption": "30%",
            "memoryConsumption": "512MB"
        },
        {
            "id": "instance2",
            "application": "App2",
            "cpuConsumption": "45%",
            "memoryConsumption": "1GB"
        }
    ]
};
        // Update instance availability information
        document.getElementById("pieChart").innerText = '';
        document.getElementById('availableinstance').textContent =data.totalAvailable;
        document.getElementById('activeinstance').textContent = data.active;

        // Render pie chart
        renderPieChart(data.totalAvailable, data.active);

        // Update instance details
       
        // updateInstanceDetails(data.instances);
    } catch (error) {
        console.error("Error fetching data:", error);
    }
}

// Function to render pie chart
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
// Function to update instance details
function updateInstanceDetails(instances) {
    const container = document.getElementById('activedetails');
    container.innerHTML = '';  // Clear existing content

    instances.forEach(instance => {
        const instanceElement = document.createElement('div');
        instanceElement.classList.add('preview-item');

        instanceElement.innerHTML = `
            <div class="preview-thumbnail">
                <div class="preview-icon bg-warning">
                    <i class="mdi mdi-chart-pie"></i>
                </div>
            </div>
            <div class="preview-item-content d-sm-flex flex-grow">
                <div class="flex-grow">
                    <h6 class="preview-subject">${instance.id}</h6>
                    <p class="text-muted mb-0">${instance.application}</p>
                </div>
                <div class="mr-auto text-sm-right pt-2 pt-sm-0">
                    <p class="text-muted">CPU: ${instance.cpuConsumption}</p>
                    <p class="text-muted mb-0">Memory: ${instance.memoryConsumption}</p>
                </div>
            </div>
        `;

        container.appendChild(instanceElement);
    });
}
  // This function will run when the page loads
    window.onload = function() {
        // Create a simulated element with id "all"
        const element = { id: "all" };
        // Call the changeInstance function with this element
        changeInstance(element);
    };
    

