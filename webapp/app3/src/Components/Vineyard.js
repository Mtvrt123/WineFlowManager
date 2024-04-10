import React from 'react'

const API_SERVER = "/api";

//{
//  "naziv": "TEST",
//  "povrsina": "155",
//  "letozacetka": "TEST"
//}

export const addVineyard = (vineyard) => {
    return fetch(`${API_SERVER}/vinograd`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(vineyard),
    }).then((response) => response.json())
    .catch((error) => console.log(error));
};

export const getVineyards = () => {
    return fetch(`${API_SERVER}/vinogradi`)
        .then((response) => response.json())
        .catch((error) => console.log(error));
};

export const getVineyard = (id) => {
    return fetch(`${API_SERVER}/vinograd/${id}`)
        .then((response) => response.json())
        .catch((error) => console.log(error));
};

export const updateVineyard = (vineyard) => {
    return fetch(`${API_SERVER}/vinograd/${vineyard.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(vineyard),
    }).then((response) => response)
    .catch((error) => console.log(error));

};

export const deleteVineyard = (id) => {
    return fetch(`${API_SERVER}/vinograd/${id}`, {
        method: "DELETE",
    }).then((response) => response.json())
    .catch((error) => console.log(error));
};

export const outputVineyards = (vineyards) => {
    const vineyardsDiv = document.getElementById("vineyardsOUTPUT");
    vineyardsDiv.innerHTML = "";

    //is array
    if (Array.isArray(vineyards)) {
        vineyards.forEach((vineyard) => {
            const vineyardDiv = document.createElement("div");
            vineyardDiv.innerHTML = `Vineyard ID: ${vineyard.id} | Vineyard Name: ${vineyard.naziv}`;
            vineyardsDiv.appendChild(vineyardDiv);
        });
    }
    //is object
    else {
        const vineyardDiv = document.createElement("div");
        vineyardDiv.innerHTML = `Vineyard ID: ${vineyards.id} | Vineyard Name: ${vineyards.naziv}`;
        vineyardsDiv.appendChild(vineyardDiv);
    }
};


export let currentVineyardId = 0;

const Vineyard = () => {
    return (
        <div>
            <div className="container mt-5">
                <h1>Vineyard ADD</h1>
                <div className="row">
                    <div className="col-md-6">
                        <form>
                            <div className="mb-3">
                                <label
                                    htmlFor="nazivVineyard"
                                    className="form-label"
                                >
                                    Naziv
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="nazivVineyard"
                                />
                            </div>
                            <div className="mb-3">
                                <label
                                    htmlFor="povrsina"
                                    className="form-label"
                                >
                                    Povrsina
                                </label>
                                <input
                                    type="number"
                                    className="form-control"
                                    id="povrsina"
                                />
                            </div>
                            <div className="mb-3">
                                <label
                                    htmlFor="letozacetka"
                                    className="form-label"
                                >
                                    Leto zacetka
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="letozacetka"
                                />
                            </div>

                            <br />

                            <button
                                type="button"
                                className="btn btn-primary"
                                onClick={() => {
                                    let yard = {
                                        naziv: document.getElementById(
                                            "nazivVineyard"
                                        ).value,
                                        povrsina:
                                            document.getElementById("povrsina")
                                                .value,
                                        letozacetka:
                                            document.getElementById(
                                                "letozacetka"
                                            ).value,
                                    };
                                    console.log(yard);
                                    addVineyard(yard)
                                        .then((data) => {
                                            console.log(data);
                                            outputVineyards(data);
                                            currentVineyardId = data.id;
                                        })
                                        .catch((error) => console.log(error));
                                }}
                            >
                                Add wine
                            </button>
                        </form>
                    </div>
                </div>

                <h1>TESTs</h1>

                <button
                    type="button"
                    className="btn btn-success"
                    onClick={() => {
                        addVineyard({
                            naziv: "TEST",
                            povrsina: "155",
                            letozacetka: "TEST",
                        }).then((data) => {
                            console.log(data);
                            outputVineyards(data);
                            currentVineyardId = data.id;
                        });
                    }}
                >
                    Add vineyard
                </button>

                <button
                    type="button"
                    className="btn btn-info"
                    onClick={() => {
                        getVineyards().then((data) => {
                            console.log(data);
                            outputVineyards(data);
                        });
                    }}
                >
                    Get vineyards
                </button>

                <button
                    type="button"
                    className="btn btn-info"
                    onClick={() => {
                        getVineyard(currentVineyardId).then((data) => {
                            console.log(data);
                            outputVineyards(data);
                        });
                    }}
                >
                    Get vineyard
                </button>

                <button
                    type="button"
                    className="btn btn-warning"
                    onClick={() => {
                        updateVineyard({
                            id: currentVineyardId,
                            naziv: "TEST",
                            povrsina: "155",
                            letozacetka: "TEST",
                        }).then((data) => {
                            console.log(data);

                            vineyardsOUTPUT.innerHTML = "";
                            const vineyardDiv = document.createElement("div");
                            vineyardDiv.innerHTML = "Vineyard updated";
                            vineyardsOUTPUT.appendChild(vineyardDiv);
                        });
                    }}
                >
                    Update vineyard
                </button>

                <button
                    type="button"
                    className="btn btn-danger"
                    onClick={() => {
                        deleteVineyard(currentVineyardId).then((data) => {
                            console.log(data);
                            
                            vineyardsOUTPUT.innerHTML = "";
                            const vineyardDiv = document.createElement("div");
                            vineyardDiv.innerHTML = "Vineyard deleted";
                            vineyardsOUTPUT.appendChild(vineyardDiv);
                        });
                    }}
                >
                    Delete vineyard
                </button>

                <div id="vineyardsOUTPUT"></div>
            </div>
        </div>
    );
};


export default Vineyard;

