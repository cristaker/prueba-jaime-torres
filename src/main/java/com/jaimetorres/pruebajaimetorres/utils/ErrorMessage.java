package com.jaimetorres.pruebajaimetorres.utils;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    DATE_FORMAT_INVALID("La fecha proporcionada tiene un formato incorrecto. Usa MM/dd/yyyy."),
    FILE_PROCESSING_ERROR("Ocurrió un error al procesar el archivo."),
    FILE_TOO_LARGE("El archivo cargado excede el tamaño máximo permitido."),
    VALIDATION_FAILED("Uno o más campos no cumplen con las validaciones requeridas."),
    GENERAL_SERVER_ERROR("Ha ocurrido un error inesperado en el servidor."),
    STICKER_NOT_FOUND("Sticker no encontrado");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}

