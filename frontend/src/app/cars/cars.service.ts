import { Injectable } from '@angular/core'

@Injectable()
export class CarsService {
    getCars() {
        return cars_data;
    }

    getCar(id:number) {
        return cars_data.find(car => car.id == id );
    }
}

const cars_data = [
    {
        id: 1,
        makes: "Mazda",
        model: "CX-3",
        year: "2019"
    },
    {
        id: 2,
        makes: "Nissan",
        model: "Maxima",
        year: "2019"
    },
    {
        id: 3,
        makes: "Kia",
        model: "Optima",
        year: "2019"
    },
    {
        id: 4,
        makes: "Mazda",
        model: "CX-3",
        year: "2018"
    },
    {
        id: 5,
        makes: "Nissan",
        model: "Maxima",
        year: "2018"
    },
    {
        id: 6,
        makes: "Kia",
        model: "Optima",
        year: "2018"
    },
    {
        id: 7,
        makes: "Mazda",
        model: "CX-3",
        year: "2017"
    },
    {
        id: 8,
        makes: "Nissan",
        model: "Maxima",
        year: "2017"
    },
    {
        id: 9,
        makes: "Kia",
        model: "Optima",
        year: "2017"
    }
]