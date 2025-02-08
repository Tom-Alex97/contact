document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("contact-form");
    const messageList = document.getElementById("public-messages");

    if (!form || !messageList) {
        console.error("🚨 contact-form 或 public-messages 未找到！");
        return;
    }

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = {
            name: document.getElementById("name").value,
            message: document.getElementById("message").value,
            isPrivate: document.querySelector('input[name="privacy"]:checked').value === "true"
        };
        console.log("📩 发送数据:", formData); // ✅ 确保 isPrivate 正确
        fetch("/api/messages/submit", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
        })
        .then(response => response.text())
        .then(data => {
            alert(data);
            loadPublicMessages(); // ✅ 重新加载公开留言
        })
        .catch(error => console.error("🚨 发生错误:", error));
    });

    function loadPublicMessages() {
        fetch("/api/messages/public")
        .then(response => response.json())
        .then(messages => {
            messageList.innerHTML = ""; // 清空列表
            messages.forEach(msg => {
                const li = document.createElement("li");
                li.textContent = `${msg.name} さん: ${msg.message}`;
                messageList.appendChild(li);
            });
        })
        .catch(error => console.error("🚨 公开留言加载失败:", error));
    }

    loadPublicMessages(); // ✅ 页面加载时获取公开留言
});