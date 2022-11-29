const form = document.getElementById('form');
const rInPixels = 150.0;
const canvas = document.getElementById("canvas");
const slider = document.getElementById("form:R-input_hidden");
const bttnsSub = document.getElementsByClassName("submit");
const bttnsClear = document.getElementsByClassName("clear");
let currentRSScalar = 0;
setInterval(renderCanvas, 250);

function renderCanvas() {
    currentRSScalar = parseFloat(slider.value) / 4;
    const ctx = canvas.getContext("2d");
    const img = new Image();

    img.src = "resources/imgs/graph.png";
    img.onload = function () {
        ctx.drawImage(img, 0, 0, 400, 400);
        ctx.font = "20px monospace";
        ctx.fillStyle = "black";
        ctx.textAlign = "center";
        ctx.fillText(currentRSScalar, 50, 190);
        ctx.fillText(currentRSScalar / 2, 125, 190);
        ctx.fillText(currentRSScalar / 2, 275, 190);
        ctx.fillText(currentRSScalar, 350, 190);
        ctx.textAlign = "left";
        ctx.fillText(currentRSScalar, 210, 55);
        ctx.fillText(currentRSScalar / 2, 210, 130);
        ctx.fillText(currentRSScalar / 2, 210, 280);
        ctx.fillText(currentRSScalar, 210, 355);
        drawAllPoints(ctx)
    }
}

function drawPoint(x, y, color, ctx) {
    ctx.beginPath();
    ctx.arc(x, y, 5, 0, 2 * Math.PI);
    ctx.fillStyle = color;
    ctx.fill();
    ctx.closePath();
}


function drawAllPoints(ctx) {
    const drawPointsOfColor = function (list, color) {
        for (let i = 0; i < list.length; i++) {
            const cells = list[i].getElementsByClassName("table_cell");
            const xSpan = cells[1].getElementsByTagName("span");
            const ySpan = cells[2].getElementsByTagName("span");
            const rSpan = cells[3].getElementsByTagName("span");
            const x = parseFloat(xSpan[0].innerHTML);
            const y = parseFloat(ySpan[0].innerHTML);
            const r = parseFloat(rSpan[0].innerHTML);
            if (r === currentRSScalar) {
                drawPoint(x * rInPixels / currentRSScalar + 200, 200 - y * rInPixels / currentRSScalar, color, ctx);
            }
        }
    }
    const hit_rows = document.getElementsByClassName("hit_row");
    const miss_rows = document.getElementsByClassName("miss_row");
    drawPointsOfColor(hit_rows, "green");
    drawPointsOfColor(miss_rows, "red");
}

bttnsSub[0].addEventListener("click", function () {
    renderCanvas();
});
bttnsClear[0].addEventListener("click", function () {
    renderCanvas();
});

canvas.addEventListener('click', function (event) {
    const possibleX = [-4, -3, -2, -1, 0, 1, 2];

    const x = event.offsetX;
    const y = event.offsetY;
    console.log("x: " + x + " y: " + y);

    const xInArea = (x - 200) / rInPixels * currentRSScalar;
    let yInArea = (200 - y) / rInPixels * currentRSScalar;
    console.log("xInArea: " + xInArea + " yInArea: " + yInArea);

    const closestX = possibleX.reduce(function (prev, curr) {
            return (Math.abs(curr - xInArea) < Math.abs(prev - xInArea) ? curr : prev);
        }
    );
    if (yInArea > 3) {
        yInArea = 3;
    } else if (yInArea < -5) {
        yInArea = -5;
    } else {
        yInArea = Math.round(yInArea * 1000) / 1000;
    }
    console.log("closestX: " + closestX + " yInArea: " + yInArea);


    const checkboxes = document.getElementsByClassName("X-input");
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            checkboxes[i].checked = false;
        }
    }
    const checkbox = document.getElementById("form:x" + closestX);
    checkbox.checked = true;
    const yInput = document.getElementById("form:Y-input");
    yInput.value = yInArea;


    bttnsSub[0].click();
});