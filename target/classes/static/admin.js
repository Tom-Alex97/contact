document.addEventListener("DOMContentLoaded", function () {
    const messageTable = document.getElementById("admin-messages");

    function loadAllMessages() {
        fetch("/api/messages/all")
        .then(response => response.json())
        .then(messages => {
            messageTable.innerHTML = ""; // 清空列表
            messages.forEach(msg => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${msg.id}</td>
                    <td>${msg.name}</td>
                    <td>${msg.message}</td>
                    <td>${msg.isPrivate ? "私密" : "公開"}</td>
                    <td>
                        <button onclick="deleteMessage(${msg.id})">削除</button>
                    </td>
                `;
                messageTable.appendChild(row);
            });
        })
        .catch(error => console.error("🚨 管理员留言加载失败:", error));
    }

    window.deleteMessage = function (id) {
        fetch(`/admin/delete/${id}`, { method: "POST" })
        .then(() => loadAllMessages())
        .catch(error => console.error("🚨 删除失败:", error));
    };

    loadAllMessages(); // ✅ 页面加载时获取所有留言
});