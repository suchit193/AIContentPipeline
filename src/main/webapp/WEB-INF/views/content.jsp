<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

<title>AI Content Studio</title>

<link href=
"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container mt-5">

<h1 class="mb-4">

AI Content Studio

</h1>

<form>

<input type="hidden"
value="${workspaceId}"
id="workspaceId">

<!-- PLATFORM -->

<label>Platform</label>

<select class="form-control mb-3"
id="platform">

<option>Instagram</option>

<option>LinkedIn</option>

<option>Twitter/X</option>

</select>

<!-- BRAND NAME -->

<label>Brand Name</label>

<input type="text"
class="form-control mb-3"
id="brandName"
placeholder="Enter Brand Name">

<!-- BRAND DETAILS -->

<label>Brand Details</label>

<textarea class="form-control mb-3"
id="brandDetails"
placeholder="Describe your brand, product or startup"></textarea>

<!-- TARGET AUDIENCE -->

<label>Target Audience</label>

<input type="text"
class="form-control mb-3"
id="audience"
placeholder="Startup founders, students, marketers...">

<!-- TONE -->

<label>Tone</label>

<input type="text"
class="form-control mb-3"
id="tone"
placeholder="Professional/Funny/Casual">

<!-- CONTENT GOAL -->

<label>Content Goal</label>

<textarea class="form-control mb-3"
id="goal"
placeholder="Promote AI startup"></textarea>

<!-- BUTTONS -->

<button type="button"
class="btn btn-success"
onclick="generateContent()">

Generate Content

</button>

<button type="button"
class="btn btn-warning"
onclick="generateImage()">

Generate Image

</button>

</form>

<hr>

<!-- LOADING -->

<div id="loading"
style="display:none;">

<h4>

Generating AI Content...

</h4>

</div>

<!-- GENERATED CONTENT -->

<div class="mt-4">

<h3>Generated Content</h3>

<textarea class="form-control"
rows="10"
id="outputBox"></textarea>

</div>

<!-- IMAGE SECTION -->

<hr>

<h3>Generated AI Image</h3>

<img id="generatedImage"

class="img-fluid rounded mt-3"

style="display:none;
max-height:400px;">

<!-- SCHEDULE -->

<br><br>

<label>

Schedule Content

</label>

<input type="datetime-local"
class="form-control mb-3"
id="scheduleTime">

<!-- ACTION BUTTONS -->

<button type="button"
class="btn btn-info"
onclick="previewContent()">

Preview

</button>

<button type="button"
class="btn btn-primary"
onclick="scheduleContent()">

Schedule Post

</button>

<hr>

<!-- CONTENT HISTORY -->

<h3>Generated Content History</h3>

<c:forEach var="item"
items="${contentList}">

<div class="card bg-secondary p-3 mt-3">

<h5>${item.brandName}</h5>

<p>${item.platform}</p>

<textarea class="form-control mb-3"
rows="5">

${item.generatedContent}

</textarea>

<a href="/AIContentPipeline/content/delete/${item.id}?workspaceId=${workspaceId}"

class="btn btn-danger">

Delete

</a>

</div>

</c:forEach>

</div>

<!-- PREVIEW MODAL -->

<div class="modal fade"
id="previewModal"
tabindex="-1">

<div class="modal-dialog">

<div class="modal-content bg-dark text-white">

<div class="modal-header">

<h5 class="modal-title">

Content Preview

</h5>

<button type="button"
class="btn-close btn-close-white"
data-bs-dismiss="modal"></button>

</div>

<div class="modal-body">

<div id="previewBody"></div>

</div>

</div>

</div>

</div>

<!-- BOOTSTRAP JS -->

<script src=
"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>

// GENERATE CONTENT

async function generateContent() {

    document.getElementById(
            "loading").style.display =
            "block";

    try {

        let workspaceId =
                document.getElementById(
                        "workspaceId").value;

        let platform =
                document.getElementById(
                        "platform").value;

        let brandName =
                document.getElementById(
                        "brandName").value;

        let brandDetails =
                document.getElementById(
                        "brandDetails").value;

        let audience =
                document.getElementById(
                        "audience").value;

        let tone =
                document.getElementById(
                        "tone").value;

        let goal =
                document.getElementById(
                        "goal").value;

        let response = await fetch(

            "/AIContentPipeline/content/generate",

            {

                method: "POST",

                headers: {

                    "Content-Type":
                    "application/x-www-form-urlencoded"
                },

                body:

                    "workspaceId=" + workspaceId +

                    "&platform=" + encodeURIComponent(
                            platform) +

                    "&brandName=" + encodeURIComponent(
                            brandName) +

                    "&brandDetails=" + encodeURIComponent(
                            brandDetails) +

                    "&audience=" + encodeURIComponent(
                            audience) +

                    "&tone=" + encodeURIComponent(
                            tone) +

                    "&goal=" + encodeURIComponent(
                            goal)
            }
        );

        let result =
                await response.text();

        document.getElementById(
                "outputBox").value =
                result;

    } catch(error) {

        console.log(error);

        document.getElementById(
                "outputBox").value =

                "Error generating AI content.";
    }

    document.getElementById(
            "loading").style.display =
            "none";
}

// GENERATE IMAGE

async function generateImage() {

    let brandName =

        document.getElementById(
                "brandName").value;

    let brandDetails =

        document.getElementById(
                "brandDetails").value;

    let imagePrompt =

        brandName +

        " " +

        brandDetails +

        " futuristic marketing artwork";

    let imageUrl =

        "https://image.pollinations.ai/prompt/"

        + encodeURIComponent(imagePrompt);

    let imageElement =

        document.getElementById(
                "generatedImage");

    imageElement.src = imageUrl;

    imageElement.style.display =
            "block";
}

// PREVIEW CONTENT

function previewContent() {

    let content =

        document.getElementById(
                "outputBox").value;

    document.getElementById(
            "previewBody").innerText =
            content;

    let modal =

        new bootstrap.Modal(

            document.getElementById(
                    "previewModal")
        );

    modal.show();
}

// SCHEDULE CONTENT

function scheduleContent() {

    let scheduleTime =

        document.getElementById(
                "scheduleTime").value;

    if(scheduleTime == "") {

        alert(
        "Please select date and time");

        return;
    }

    alert(

        "Content Scheduled Successfully for:\n\n"

        + scheduleTime
    );
}

</script>

</body>

</html>