const baseUrl = "http://localhost:8080/api/books";

// Function to fetch all books
function fetchBooks() {
    fetch(baseUrl)
        .then(response => response.json())
        .then(data => displayBooks(data))
        .catch(error => console.error('Error fetching books:', error));
}

// Function to display books in a table
function displayBooks(books) {
    const mainContent = document.getElementById("main-content");
    let table = `
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>ISBN</th>
                    <th>Issued</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
    `;

    books.forEach(book => {
        table += `
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>${book.isIssued ? 'Yes' : 'No'}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editBook(${book.id})">Edit</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteBook(${book.id})">Delete</button>
                </td>
            </tr>
        `;
    });

    table += `
            </tbody>
        </table>
        <button class="btn btn-primary mt-3" onclick="showAddBookForm()">Add Book</button>
    `;
    mainContent.innerHTML = table;
}

// Function to show the add book form
function showAddBookForm() {
    const mainContent = document.getElementById("main-content");
    mainContent.innerHTML = `
        <form id="addBookForm">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" id="title" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="author" class="form-label">Author</label>
                <input type="text" id="author" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="isbn" class="form-label">ISBN</label>
                <input type="text" id="isbn" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="isIssued" class="form-label">Issued</label>
                <select id="isIssued" class="form-select">
                    <option value="false">No</option>
                    <option value="true">Yes</option>
                </select>
            </div>
            <button type="submit" class="btn btn-success">Add Book</button>
        </form>
    `;

    document.getElementById("addBookForm").onsubmit = function(event) {
        event.preventDefault();
        addBook();
    };
}

// Function to add a new book
function addBook() {
    const book = {
        title: document.getElementById("title").value,
        author: document.getElementById("author").value,
        isbn: document.getElementById("isbn").value,
        isIssued: document.getElementById("isIssued").value === "true"
    };

    fetch(baseUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(book)
    })
        .then(() => fetchBooks())
        .catch(error => console.error('Error adding book:', error));
}

// Function to delete a book
function deleteBook(id) {
    fetch(`${baseUrl}/${id}`, { method: "DELETE" })
        .then(() => fetchBooks())
        .catch(error => console.error('Error deleting book:', error));
}

// Load books on page load
document.addEventListener("DOMContentLoaded", fetchBooks);
