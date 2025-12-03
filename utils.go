package main

import (
	"encoding/json"
	"net/http"
)

func respondWithError(w http.ResponseWriter, code int, message string) {
	type response struct {
		Error string `json:"error"`
	}

	respondWithJSON(w, code, response{Error: message})
}

func respondWithJSON(w http.ResponseWriter, code int, payload any) {
	w.WriteHeader(code)
	json.NewEncoder(w).Encode(payload)
}
