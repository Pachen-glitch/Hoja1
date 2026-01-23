from tkinter import *

import requests

class BackendRadio:
    BASE_URL = "http://localhost:8000" # se define la url que usara el servidor

    def encender(self):
        return requests.get(f"{self.BASE_URL}/encender").text # los request que se haran al servidor

    def apagar(self):
        return requests.get(f"{self.BASE_URL}/apagar").text

    def cambiarBanda(self):
        return requests.get(f"{self.BASE_URL}/cambiar").text

    def avanzarEstacion(self):
        return requests.get(f"{self.BASE_URL}/avanzar").text

    def usarFavorito(self, pos):
        return requests.get(
            f"{self.BASE_URL}/favorito?pos={pos}"
        ).text


class RadioApp(Tk):
    def __init__(self):
        super().__init__()
        self.title("Radio AM / FM")
        self.geometry("600x350")
        self.resizable(False, False)
        self.backend = BackendRadio()
    
        

        #  Pantalla 
        self.display = Label(
            text="APAGADA",
            font=("Courier", 24),
            bg="black",
            fg="green",
            width=20,
            height=2
        )
        self.display.pack(pady=10)

        #  Botones de encendido 
        power_frame = Frame()
        power_frame.pack(pady=5)

        Button(
            power_frame,
            text="ENCENDER",
            bg="green",
            fg="white",
            width=12,
            command=self.encender
        ).grid(row=0, column=0, padx=10)

        Button(
            power_frame,
            text="APAGAR",
            bg="red",
            fg="white",
            width=12,
            command=self.apagar
        ).grid(row=0, column=1, padx=10)

        #  Favoritos 
        fav_frame = LabelFrame( text="Favoritos", padx=10, pady=5)
        fav_frame.pack(pady=10)

        self.favoritos = []

        for i in range(12):
            btn = Button(
                fav_frame,
                text=f"{i+1}",
                width=5,
                command=lambda i=i: self.usar_favorito(i)
            )
            btn.grid(row=i//6, column=i%6, padx=5, pady=5)

        #  Controles 
        control_frame = Frame()
        control_frame.pack(pady=10)

        Button(
            control_frame,
            text="AM / FM",
            width=10,
            command=self.cambiar_banda
        ).grid(row=0, column=0, padx=10)

        Button(
            control_frame,
            text="AVANZAR ▶",
            width=10,
            command=self.avanzar_estacion
        ).grid(row=0, column=1, padx=10)

    #  Funciones 
    def encender(self):
        texto = self.backend.encender()
        self.display.config(text=texto)

    def apagar(self):
        texto = self.backend.apagar()
        self.display.config(text=texto)

    def cambiar_banda(self):
        texto = self.backend.cambiarBanda()
        self.display.config(text=texto)

    def avanzar_estacion(self):
        texto = self.backend.avanzarEstacion()
        self.display.config(text=texto)

    def usar_favorito(self, pos):
        texto = self.backend.usarFavorito(pos)
        self.display.config(text=texto)

    def cargar_favorito(self, pos):
        texto = self.backend.cargarFavorito(pos)
        self.display.config(text=texto)
a= RadioApp()
a.mainloop()