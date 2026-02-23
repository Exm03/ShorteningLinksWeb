async function loadLinks() {

    const response = await fetch("/api/links")
    const data = await response.json()

    const container = document.getElementById("links")
    container.innerHTML = ""

    data.forEach(link => {

        const div = document.createElement("div")
        div.className = "link-card"

        div.innerHTML = `
        <div>
            <a href="${link.original}" target="_blank">
                ${link.shortLink}
            </a>
            </div>
            <div style="opacity:0.7;font-size:13px">
                ${link.original}
            </div>
            <button class="delete-btn" onclick="deleteLink(${link.id})">Delete</button>`

            container.prepend(div)
    })
}

async function addLink() {

    const original = document.getElementById("original").value
    const shortLink = document.getElementById("shortLink").value
    const message = document.getElementById("message")

    try {
        const response = await fetch("/api/links", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ original: original,
             shortLink: shortLink })
        })

        if (!response.ok) {
            const error = await response.json()
            message.style.color = "red"
            message.innerText = error.error
            return
        }

        message.style.color = "lightgreen"
        message.innerText = "Link added successfully!"

        document.getElementById("original").value = ""
        document.getElementById("shortLink").value = ""
        
        loadLinks()
    } catch (e) {
        message.style.color = "red"
        message.innerText = "An error occurred while adding the link."
    }
    
}

async function deleteLink(id) {
    try {
        const response = await fetch(`/api/links/${id}`, {
            method: "DELETE"
        });

        const result = await response.json();

        if (response.ok) {
            loadLinks();
        } else {
            const message = document.getElementById("message");
            message.style.color = "red";
            message.innerText = result.error;
        }
    } catch (e) {
        const message = document.getElementById("message");
        message.style.color = "red";
        message.innerText = "Ошибка при удалении ссылки.";
    }
}

loadLinks();