# Tourist Directory App

## Descripción

La Tourist Directory App es una aplicación móvil Android Nativo, como reto técnico para la empresa  Gestion Source, este aplicativo permite a los usuarios explorar lugares turísticos. La aplicación incluye una pantalla de splash, un listado de lugares, detalles del lugar seleccionado y una pantalla de mapa para visualizar la ubicación del lugar.

## Características

1. **Pantalla de Splash**
    - Carga datos de configuración del proyecto (simulación).
    - Duración de 2-3 segundos.
    - Incluye el logo de la aplicación y una barra de progreso o animación.
    - Muestra el entorno actual (desarrollo, producción, etc.).

2. **Listado de Lugares**
    - Muestra una lista de lugares turísticos.
    - Cada ítem de la lista contiene una imagen, un título y una pequeña descripción.
    - Navega a la pantalla de detalle del lugar al hacer clic en un ítem.

3. **Pantalla de Detalle del Lugar**
    - Muestra información detallada del lugar seleccionado.
    - Incluye una imagen en tamaño grande, título, descripción detallada, dirección y otra información relevante.

4. **Pantalla con Mapa**
    - Visualiza la ubicación del lugar turístico en un mapa.
    - Integra Google Maps con un marcador en la ubicación del lugar turístico.

## Arquitectura

Se ha utilizado el patrón de arquitectura MVVM (Model-View-ViewModel) y Clean Architecture para separar las responsabilidades y facilitar el mantenimiento y escalabilidad del código.

### Justificación de MVVM
- **Separación de responsabilidades**: MVVM ayuda a separar la lógica de negocio de la lógica de UI.
- **Reutilización de código**: Los ViewModels pueden ser reutilizados en diferentes vistas.
- **Testabilidad**: La lógica en los ViewModels se puede probar de manera independiente.

## Tecnologías Utilizadas

- **Kotlin**
- **MVVM**: Separación clara entre la vista, el modelo y el ViewModel.
- **Clean Architecture**: Divide el proyecto en capas (data, domain, presentation) para una mejor organización.
- **Coroutines**: Manejo de operaciones asíncronas de manera eficiente.
- **Retrofit**: Comunicación con APIs REST.
- **Room**: Almacenamiento de datos local.
- **Hilt**: Inyección de dependencias.
- **Google Maps SDK**: Integración de mapas.
- **LiveData**: Observación de datos reactivos.
- **ViewBinding**: Acceso seguro a vistas.
- **Product Flavors**: Configuración para soportar múltiples entornos (desarrollo, producción, etc.).

## Principios SOLID y Buenas Prácticas

- **Single Responsibility Principle (SRP)**: Cada clase tiene una única responsabilidad.
- **Open/Closed Principle (OCP)**: El código está diseñado para ser extensible sin modificar las clases existentes.
- **Liskov Substitution Principle (LSP)**: Las subclases pueden reemplazar a las superclases sin alterar el comportamiento correcto del programa.
- **Interface Segregation Principle (ISP)**: Las interfaces específicas son preferibles a las interfaces generales.
- **Dependency Inversion Principle (DIP)**: Las dependencias de alto nivel no dependen de módulos de bajo nivel.

## Documentación y Comentarios

Se han incluido comentarios en el código para explicar las decisiones de diseño y se ha proporcionado documentación mínima para la configuración y ejecución del proyecto.

## Repositorio

El proyecto está disponible en [GitHub](https://github.com/fernandopretell/TouristDirectory).

## Configuración del Proyecto

### Requisitos

- Android Studio Arctic Fox o superior.
- SDK de Android 21 o superior.
- Conexión a Internet para cargar los datos de los lugares.

### Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/fernandopretell/TouristDirectory.git

## Contacto

Para cualquier consulta o sugerencia, por favor contacta a [fernandopretell93@gmail.com](fernandopretell93@gmail.com).
