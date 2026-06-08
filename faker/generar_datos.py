from faker import Faker
import random
import uuid
from datetime import date

fecha_actual = date.today()
anio_actual = fecha_actual.year

# Inicializar Faker en español
fake = Faker('es_ES')


# 1. Lista unificada de categorías como diccionarios (12 en total)
# Se asigna un ID del 1 al 12 para que coincida con el AUTO_INCREMENT de MySQL
categorias = [
    {"id": 1, "nombre": "Laptop", "codigo": "LAP"},
    {"id": 2, "nombre": "Monitor", "codigo": "MON"},
    {"id": 3, "nombre": "Teclado", "codigo": "TEC"},
    {"id": 4, "nombre": "Mouse", "codigo": "MOU"},
    {"id": 5, "nombre": "Computadora de escritorio", "codigo": "CPU"},
    {"id": 6, "nombre": "Servidores", "codigo": "SER"},
    {"id": 7, "nombre": "Tablets", "codigo": "TAB"},
    {"id": 8, "nombre": "Teléfonos VoIP", "codigo": "VOI"},
    {"id": 9, "nombre": "Switches", "codigo": "SWI"},
    {"id": 10, "nombre": "Routers", "codigo": "ROU"},
    {"id": 11, "nombre": "Impresoras", "codigo": "IMP"}
]

estados = [
    "Disponible",
    "Asignado",
    "En Mantenimiento",
    "Baja",
]

marcas_modelos = [
    "DELL", "HP", "ASUS", "Logitech", "Lenovo"
]

anios = [2024, 2025, 2026]

# Nombre del archivo de salida
nombre_archivo = "inserts_inventario.sql"

# Abrir el archivo en modo escritura con codificación UTF-8
with open(nombre_archivo, "w", encoding="utf-8") as file:
    
    # ==========================================
    # GENERAR INSERTS PARA CATEGORÍAS
    # ==========================================
    file.write("-- ==========================================\n")
    file.write("-- INSERTS PARA: inventario.categorias\n")
    file.write("-- ==========================================\n")
    file.write("INSERT INTO inventario.categorias (id, nombre, codigo_prefijo) VALUES\n")
    
    total_categorias = len(categorias)
    for i, cat in enumerate(categorias, 1):
        comma = "," if i < total_categorias else ";"
        file.write(f"({cat['id']}, '{cat['nombre']}', '{cat['codigo']}'){comma}\n")

    # ==========================================
    # GENERAR INSERTS PARA ACTIVOS TECNOLÓGICOS
    # ==========================================
    file.write("\n-- ==========================================\n")
    file.write("-- INSERTS PARA: activos_tecnologicos\n")
    file.write("-- ==========================================\n")
    file.write("INSERT INTO activos_tecnologicos (identificador_tecnico, folio_inventario, numero_de_serie, marca_modelo, estado, costo_adquisicion, fecha_hora, categoria) VALUES\n")

    for i in range(1, 121):
        # Elegir una categoría aleatoria de la lista
        categoria_map = random.choice(categorias)
        anio_folio = random.choice(anios)
        
        tech_id = str(uuid.uuid4())
        
        # Folio dinámico: CODIGO-AÑO-NUMERO (ej: LAP-2025-001)
        folio = f"{categoria_map['codigo']}-{anio_folio}-{i:03d}"
        
        # Garantizamos unicidad en numero_de_serie
        serie = f"SN-{uuid.uuid4().hex[:8].upper()}-{random.randint(10000, 99999)}"
        
        marca = random.choice(marcas_modelos)
        estado = random.choice(estados)
        costo = round(random.uniform(50.0, 4500.0), 2)
        
        # Fechas entre hace 3 años y la fecha actual
        fecha_inicio = date(anio_folio,1,1)
        if anio_folio == anio_actual:
            fecha_fin = date(anio_folio,12,31)
        else:
            fecha_fin = fecha_actual
        print(f"{fecha_inicio} a {fecha_fin}")
        fecha = fake.date_time_between_dates(datetime_start = fecha_inicio, datetime_end = fecha_fin).strftime('%Y-%m-%d %H:%M:%S')
        
        
        # La categoría se vincula al ID real de la categoría seleccionada
        cat_id = categoria_map["id"]

        comma = "," if i < 120 else ";"
        file.write(f"('{tech_id}', '{folio}', '{serie}', '{marca}', '{estado}', {costo}, '{fecha}', {cat_id}){comma}\n")

print(f"✅ ¡Éxito! Se han generado los datos y guardado en el archivo: '{nombre_archivo}'")