import React from 'react'

const API_SERVER = "/api";

//{
//  "ime": "TEST",
//  "priimek": "TEST",
//  "email": "TEST"
//}

export const getUsers = (id) =>
    fetch(`${API_SERVER}/uporabnik/get/${id}`, {
        method: "GET",
    }).then((res) => res.json())
    .catch((error) => console.log(error));

export const addUser = (user) =>
    fetch(`${API_SERVER}/uporabnik/add`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
    }).then((res) => {
        if (res.ok) {
            return res.json();
        } else {
            throw new Error('Server response wasnt OK');
        }
    }).catch((error) => console.log(error));

export const updateUser = (user) =>
    fetch(`${API_SERVER}/uporabnik/update`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
    }).then((res) => res.json())
    .catch((error) => console.log(error));

export const deleteUser = (id) =>
    fetch(`${API_SERVER}/uporabnik/delete/${id}`, {
        method: "DELETE",
    }).then((res) => res.json())
    .catch((error) => console.log(error));


export const outputUsers = (user) => {
    const usersOutput = document.getElementById("usersOUTPUT");
    usersOutput.innerHTML = "";

    const userDiv = document.createElement("div");
    userDiv.innerHTML = `ID: ${user.id}, IME: ${user.ime}, PRIIMEK: ${user.priimek}, EMAIL: ${user.email}`;
    usersOutput.appendChild(userDiv);
    
}

export let currentUserId = 0;

const Users = () => {
    return (
        <div>
            <div className="container mt-5">
                <h1>User ADD</h1>
                <div className="row">
                    <div className="col-md-6">
                        <form>
                            <div className="form-group">
                                <label htmlFor="ime">Ime:</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="ime"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="priimek">Priimek:</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="priimek"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="email">Email:</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="email"
                                />
                            </div>

                            <br />

                            <button
                                type="button"
                                className="btn btn-primary"
                                onClick={() => {
                                    const ime =
                                        document.getElementById("ime").value;
                                    const priimek =
                                        document.getElementById(
                                            "priimek"
                                        ).value;
                                    const email =
                                        document.getElementById("email").value;

                                    addUser({ ime, priimek, email }).then(
                                        (data) => {
                                            console.log(data);
                                            outputUsers(data);
                                            currentUserId = data.id;
                                        }
                                    );
                                }}
                            >
                                Add user
                            </button>
                        </form>
                    </div>
                </div>

                <h1>TESTs</h1>
                <button
                    type="button"
                    className="btn btn-success"
                    onClick={() =>
                        addUser({
                            ime: "TEST",
                            priimek: "TEST",
                            email: "TEST",
                        })
                            .then((data) => {
                                console.log(data);
                                outputUsers(data);
                                currentUserId = data.id;
                            })
                            .catch((error) => console.log(error))
                    }
                >
                    Add user
                </button>
                <button
                    type="button"
                    className="btn btn-info"
                    onClick={() =>
                        getUsers(currentUserId)
                            .then((data) => outputUsers(data))
                            .catch((error) => console.log(error))
                    }
                >
                    Get user
                </button>
                <button
                    type="button"
                    className="btn btn-warning"
                    onClick={() =>
                        updateUser({
                            id: currentUserId,
                            ime: `TEST${currentUserId}`,
                            priimek: `TEST${currentUserId}`,
                            email: `TEST${currentUserId}`,
                        })
                            .then((data) => {
                                console.log(data);
                                outputUsers(data);
                            })
                            .catch((error) => console.log(error))
                    }
                >
                    Update user
                </button>
                <button
                    type="button"
                    className="btn btn-danger"
                    onClick={() =>
                        deleteUser(currentUserId)
                            .then((data) => {
                                console.log(data);
                                outputUsers(data);
                            })
                            .catch((error) => {
                                console.log(error);
                            })
                            .catch((error) => console.log(error))
                    }
                >
                    Delete user
                </button>

                <div id="usersOUTPUT"></div>
            </div>
        </div>
    );
}


export default Users

