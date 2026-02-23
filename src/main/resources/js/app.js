async function loadLinks() {

    const response = await fetch("/api/links");
    const data = await response.json();

    const container = document.getElementById("links");
    container.innerHTML = "";

    data.forEach(link => {

        const div = document.createElement("div");
        div.className = "link-card";

        div.innerHTML = `
            <a href="${link.original}" target="_blank">
                ${link.shortLink}
            </a>
            <div style="opacity:0.7">${link.original}</div>
        `;

        container.appendChild(div);
    });
}

async function addLink() {

    const original = document.getElementById("original").value;
    const shortLink = document.getElementById("shortLink").value;
    const message = document.getElementById("message");

    const response = await fetch("/api/links", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ original, shortLink })
    });

    if (!response.ok) {
        const error = await response.json();
        message.style.color = "red";
        message.innerText = error.error;
        return;
    }

    message.style.color = "lightgreen";
    message.innerText = "Link added successfully";

    document.getElementById("original").value = "";
    document.getElementById("shortLink").value = "";

    loadLinks();
}

loadLinks();