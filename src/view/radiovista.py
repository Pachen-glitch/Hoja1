from tkinter import *
from tkinter import ttk

class RadioApp(Tk):
    def __init__(self):
        super().__init__()
        self.title("Radio AM / FM")
        self.geometry("600x350")
        self.resizable(False, False)

        self.encendida = False
        self.banda = "FM"
        self.frecuencia = 88.1

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
                command=lambda i=i: self.guardar_favorito(i)
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
        self.encendida = True
        self.actualizar_display()

    def apagar(self):
        self.encendida = False
        self.display.config(text="APAGADA")

    def cambiar_banda(self):
        if not self.encendida:
            return
        self.banda = "AM" if self.banda == "FM" else "FM"
        self.frecuencia = 540 if self.banda == "AM" else 88.1
        self.actualizar_display()

    def avanzar_estacion(self):
        if not self.encendida:
            return
        if self.banda == "FM":
            self.frecuencia += 0.2
            if self.frecuencia > 108:
                self.frecuencia = 88.1
        else:
            self.frecuencia += 10
            if self.frecuencia > 1700:
                self.frecuencia = 540
        self.actualizar_display()

    def guardar_favorito(self, index):
        if not self.encendida:
            return
        if len(self.favoritos) <= index:
            self.favoritos.extend([None] * (index + 1 - len(self.favoritos)))
        self.favoritos[index] = (self.banda, self.frecuencia)
        print(f"Favorito {index+1} guardado:", self.favoritos[index])

    def actualizar_display(self):
        if self.encendida:
            self.display.config(
                text=f"{self.banda}  {self.frecuencia:.1f}"
            )
a= RadioApp()
a.mainloop()