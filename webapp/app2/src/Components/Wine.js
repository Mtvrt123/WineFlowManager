import React from 'react'

const API_SERVER = "/api";

//{
//  "naziv": "TEST",
//  "sorta": "TEST",
//  "proizvajalec": "TEST",
//  "drzava": "TEST",
//  "regija": "TEST",
//  "opis": "TEST"
//}

export const getWines = () =>
    fetch(`${API_SERVER}/vina`, {
        method: "GET",
    }).then((res) => res.json())
    .catch((error) => console.log(error));

export const getWine = (id) =>
    fetch(`${API_SERVER}/vina/${id}`, {
        method: "GET",
    }).then((res) => res.json())
    .catch((error) => console.log(error));
    
export const addWine = (wine) =>
    fetch(`${API_SERVER}/vina/`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(wine),
    }).then((res) => {
        if (res.ok) {
            return res.json();
        } else {
            throw new Error('Server response wasnt OK');
        }
    }).catch((error) => console.log(error));


export const updateWine = (wine) =>
    fetch(`${API_SERVER}/vina/${wine.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(wine),
    }).then((res) => res.json())
    .catch((error) => console.log(error));

export const deleteWine = (id) =>
    fetch(`${API_SERVER}/vina/${id}`, {
        method: "DELETE",
    }).then((res) => res.json())
    .catch((error) => console.log(error));

export const outputWines = (wine) => {
    const winesOutput = document.getElementById("winesOUTPUT");
    winesOutput.innerHTML = "";

    if (Array.isArray(wine)) {
        wine.forEach((wine) => {
            const wineDiv = document.createElement("div");

            wineDiv.innerHTML = `ID: ${wine.id}, NAZIV: ${wine.naziv}, SORTA: ${wine.sorta}, PROIZVAJALEC: ${wine.proizvajalec}, DRZAVA: ${wine.drzava}, REGIJA: ${wine.regija}, OPIS: ${wine.opis}`;
            winesOutput.appendChild(wineDiv);
        });
    }
    else {
        const wineDiv = document.createElement("div");

        wineDiv.innerHTML = `ID: ${wine.id}, NAZIV: ${wine.naziv}, SORTA: ${wine.sorta}, PROIZVAJALEC: ${wine.proizvajalec}, DRZAVA: ${wine.drzava}, REGIJA: ${wine.regija}, OPIS: ${wine.opis}`;
        winesOutput.appendChild(wineDiv);
    }

}

export let currentWineId = 0;

const Wine = () => {
    return (
        <div>
            <div className="container mt-5">
                <h1>Wine ADD</h1>
                <div className="row">
                    <div className="col-md-6">
                        <form>
                            <div className="form-group">
                                <label htmlFor="naziv">Naziv</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="naziv"
                                    placeholder="Naziv"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="sorta">Sorta</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="sorta"
                                    placeholder="Sorta"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="proizvajalec">
                                    Proizvajalec
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="proizvajalec"
                                    placeholder="Proizvajalec"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="drzava">Drzava</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="drzava"
                                    placeholder="Drzava"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="regija">Regija</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="regija"
                                    placeholder="Regija"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="opis">Opis</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="opis"
                                    placeholder="Opis"
                                />
                            </div>

                            <br />

                            <button
                                type="button"
                                className="btn btn-primary"
                                onClick={() => {
                                    addWine({
                                        naziv: document.getElementById("naziv")
                                            .value,
                                        sorta: document.getElementById("sorta")
                                            .value,
                                        proizvajalec:
                                            document.getElementById(
                                                "proizvajalec"
                                            ).value,
                                        drzava: document.getElementById(
                                            "drzava"
                                        ).value,
                                        regija: document.getElementById(
                                            "regija"
                                        ).value,
                                        opis: document.getElementById("opis")
                                            .value,
                                    }).then((data) => {
                                        console.log(data);
                                        outputWines(data);
                                        currentWineId = data.id;
                                    });
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
                    onClick={() =>
                        addWine({
                            naziv: "TEST",
                            sorta: "TEST",
                            proizvajalec: "TEST",
                            drzava: "TEST",
                            regija: "TEST",
                            opis: "TEST",
                        }).then((data) => {
                            console.log(data);
                            outputWines(data);
                            currentWineId = data.id;
                        })
                    }
                >
                    Add wine
                </button>

                <button
                    type="button"
                    className="btn btn-info"
                    onClick={() =>
                        getWines()
                            .then((data) => outputWines(data))
                            .catch((error) => console.log(error))
                    }
                >
                    Get wines
                </button>

                <button
                    type="button"
                    className="btn btn-info"
                    onClick={() =>{
                        
                        console.log(currentWineId);
                        getWine(currentWineId)
                            .then((data) => {
                                console.log(data);
                                outputWines(data);
                            })
                            .catch((error) => console.log(error))
                        }
                    }
                >
                    Get wine
                </button>

                <button
                    type="button"
                    className="btn btn-warning"
                    onClick={() =>
                        updateWine({
                            id: currentWineId,
                            naziv: `TEST${currentWineId}`,
                            sorta: `TEST${currentWineId}`,
                            proizvajalec: `TEST${currentWineId}`,
                            drzava: `TEST${currentWineId}`,
                            regija: `TEST${currentWineId}`,
                            opis: `TEST${currentWineId}`,
                        })
                            .then((data) => {
                                console.log(data);
                                outputWines(data);
                            })
                            .catch((error) => console.log(error))
                    }
                >
                    Update wine
                </button>

                <button
                    type="button"
                    className="btn btn-danger"
                    onClick={() =>
                        deleteWine(currentWineId)
                            .then((data) => {
                                console.log(data);
                                
                                const wineDiv = document.createElement("div");
                                wineDiv.innerHTML = `Wine with ID: ${currentWineId} deleted`;
                                document.getElementById("winesOUTPUT").appendChild(wineDiv);

                            })
                            .catch((error) => {
                                console.log(error);
                            })
                    }
                >
                    Delete wine
                </button>

                <div id="winesOUTPUT"></div>
            </div>
        </div>
    );
}


export default Wine

