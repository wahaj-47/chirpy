package main

import (
	"encoding/json"
	"net/http"
	"strings"
)

func handlerValidateChirp(w http.ResponseWriter, r *http.Request) {
	type request struct {
		Body string `json:"body"`
	}

	type response struct {
		CleanedBody string `json:"cleaned_body"`
	}

	w.Header().Set("Content-Type", "application/json")

	var req request
	if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
		respondWithError(w, http.StatusInternalServerError, "Something went wrong")
		return
	}

	if len(req.Body) > 140 {
		respondWithError(w, http.StatusBadRequest, "Chirp is too long")
		return
	}

	respondWithJSON(w, http.StatusOK, response{CleanedBody: censor(req.Body)})
}

func censor(s string) string {
	curses := map[string]bool{"kerfuffle": true, "sharbert": true, "fornax": true}
	words := strings.Split(s, " ")
	for i, w := range words {
		lower := strings.ToLower(w)
		if curses[lower] {
			words[i] = "***"
		}
	}
	return strings.Join(words, " ")
}
