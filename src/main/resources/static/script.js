const baseUrl = 'http://localhost:1111/employees'; // Replace with your API endpoint

// Get Employees
async function getEmployees() {
    try {
        const response = await fetch(baseUrl);
        if (!response.ok) throw new Error('Failed to fetch employees');
        const employees = await response.json();
        const employeeList = document.getElementById('employeeList');
        employeeList.innerHTML = '';
        employees.forEach(employee => {
            const li = document.createElement('li');
            li.innerHTML = `ID: ${employee.id} | Name: ${employee.name} | Phone: ${employee.phone} | Email: ${employee.email}`;
            employeeList.appendChild(li);
        });
    } catch (error) {
        console.error(error);
    }
}

// Create Employee
async function createEmployee(event) {
    event.preventDefault();
    const name = document.getElementById('createName').value;
    const phone = document.getElementById('createPhone').value;
    const email = document.getElementById('createEmail').value;

    const newEmployee = { name, phone, email };

    try {
        const response = await fetch(baseUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newEmployee),
        });
        if (!response.ok) throw new Error('Failed to create employee');
        alert('Employee created successfully');
    } catch (error) {
        console.error(error);
        document.getElementById('createError').textContent = error.message;
    }
}

// Update Employee
async function updateEmployee(event) {
    event.preventDefault();
    const id = document.getElementById('updateId').value;
    const name = document.getElementById('updateName').value;
    const phone = document.getElementById('updatePhone').value;
    const email = document.getElementById('updateEmail').value;

    const updatedEmployee = { name, phone, email };

    try {
        const response = await fetch(`${baseUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedEmployee),
        });
        if (!response.ok) throw new Error('Failed to update employee');
        alert('Employee updated successfully');
    } catch (error) {
        console.error(error);
        document.getElementById('updateError').textContent = error.message;
    }
}

// Partial Update Employee
async function partialUpdateEmployee(event) {
    event.preventDefault();
    const id = document.getElementById('partialUpdateId').value;
    const name = document.getElementById('partialUpdateName').value;
    const phone = document.getElementById('partialUpdatePhone').value;
    const email = document.getElementById('partialUpdateEmail').value;

    const updatedEmployee = {};
    if (name) updatedEmployee.name = name;
    if (phone) updatedEmployee.phone = phone;
    if (email) updatedEmployee.email = email;

    try {
        const response = await fetch(`${baseUrl}/${id}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedEmployee),
        });
        if (!response.ok) throw new Error('Failed to update employee');
        alert('Employee partially updated successfully');
    } catch (error) {
        console.error(error);
        document.getElementById('partialUpdateError').textContent = error.message;
    }
}

// Delete Employee
async function deleteEmployee(event) {
    event.preventDefault();
    const id = document.getElementById('deleteId').value;

    try {
        const response = await fetch(`${baseUrl}/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) throw new Error('Failed to delete employee');
        alert('Employee deleted successfully');
    } catch (error) {
        console.error(error);
        document.getElementById('deleteError').textContent = error.message;
    }
}

// Fetch permissions and roles, and update UI accordingly
async function fetchPermissionsAndRoles() {
    try {
        const response = await fetch(`${baseUrl}/permissions`);
        if (!response.ok) throw new Error('Failed to fetch permissions');
        const { permissions, role } = await response.json();

        // Hide or disable UI elements based on permissions
        if (!permissions.CREATE_EMPLOYEE) {
            const form = document.getElementById('createEmployeeForm');
            if (form) form.style.display = 'none';
        }
        if (!permissions.UPDATE_EMPLOYEE) {
            const form = document.getElementById('updateEmployeeForm');
            if (form) form.style.display = 'none';
        }
        if (!permissions.PARTIAL_UPDATE_EMPLOYEE) {
            const form = document.getElementById('partialUpdateEmployeeForm');
            if (form) form.style.display = 'none';
        }
        if (!permissions.DELETE_EMPLOYEE) {
            const form = document.getElementById('deleteEmployeeForm');
            if (form) form.style.display = 'none';
        }

        // Display role-specific UI elements
        const roleElement = document.getElementById('userRole');
        if (roleElement) {
            roleElement.textContent = `Role: ${role}`;
        }
    } catch (error) {
        console.error('Error fetching permissions and roles:', error);
    }
}

// Event listeners for form submissions
if (document.getElementById('createEmployeeForm')) {
    document.getElementById('createEmployeeForm').addEventListener('submit', createEmployee);
}
if (document.getElementById('updateEmployeeForm')) {
    document.getElementById('updateEmployeeForm').addEventListener('submit', updateEmployee);
}
if (document.getElementById('partialUpdateEmployeeForm')) {
    document.getElementById('partialUpdateEmployeeForm').addEventListener('submit', partialUpdateEmployee);
}
if (document.getElementById('deleteEmployeeForm')) {
    document.getElementById('deleteEmployeeForm').addEventListener('submit', deleteEmployee);
}
if (window.location.pathname === '/list.html') {
    getEmployees();
}

// Call fetchPermissionsAndRoles on page load
if (window.location.pathname === '/index.html') {
    fetchPermissionsAndRoles();
}
