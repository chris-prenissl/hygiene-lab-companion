package com.christophprenissl.hygienecompanion.util

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//sizes
const val halfSize = 0.5f
val cardPadding = 4.dp
val standardPadding = 8.dp
val doubleStandardPadding = standardPadding.times(2)
val standardElevation = 4.dp
val titleSize = 20.sp

//views
const val HOME_ROUTE = "home_route"
const val LOGGED_OUT_ROUTE = "logged_out_route"
const val COVERING_LETTERS_ROUTE = "covering_letter_route"
const val LAB_ROUTE = "lab_route"
const val COVERING_LETTER_BASIS_ROUTE = "covering_letter_basis_route"
const val COVERING_BASIS_ROUTE = "basis_detail_route"
const val REPORTS_ROUTE = "reports_route"
const val PROFILE_ROUTE = "profile_route"

//swipe to delete
const val SWIPE_THRESHOLD = 0.8f

//string resources
const val ADDRESS_NAME = "Addressbezeichnung"
const val ADDRESS = "Adresse"
const val ZIP = "Postleitzahl"
const val STREET = "Straße"
const val CITY_NAME = "Stadtname"
const val CONTACT = "Kontakt: "
const val CONTACT_NAME = "Kontaktperson"
const val CONTACT_DATA = "Kontaktdaten"
const val PHONE = "Telefon"
const val FAX = "Fax"
const val EMAIL = "E-Mail"
const val BASIS = "Basis"
const val NORM = "Norm/Basis"

const val CLIENT_ADDRESS = "Auftraggeber-Adresse"
const val COVERING_COMPANY_ADDRESS = "Adresse der Probeentnehmer-Firma"
const val SET_COVERING_COMPANY_ADDRESS = "Adresse der Probeentnehmer-Firma festlegen"
const val COVERING_ADDRESS = "Probeentnahme Adresse"
const val SET_COVERING_ADDRESS = "Probeentnahme Adresse festlegen"
const val CHOICES_FOR_SAMPLE_LOCATIONS = "Wahl der Probeentnahmestellen"
const val ADD_SAMPLE_LOCATION = "Probeentnahmestelle hinzufügen"
const val PLANNED_START_DATE = "Geplanter Anfang der Untersuchung"
const val CHOOSE_START_DATE = "Geplanten Anfang der Untersuchung wählen"
const val PLANNED_END_DATE = "Geplantes Ende der Untersuchung"
const val CHOOSE_PLANNED_END = "Geplantes Ende der Untersuchung wählen"
const val FREQUENCY = "Frequenz"

const val BASIC_COVERING_PARAMETERS = "Grundlegende Parameter für Probenentnahme"
const val COVERING_SAMPLE_PARAMETERS = "Probenspezifische Parameter für Probeentnahme"
const val LAB_SAMPLE_PARAMETERS = "Probenspezifische Parameter für Laborannahme"
const val BASIC_LAB_REPORT_PARAMETERS = "Grundlegende Parameter für Labor-Befund"
const val RESULT_TO_CLIENT = "Ergebnis zum Auftraggeber:"
const val RESULT_TO_COVERING_ADDRESS = "Ergebnis zu Probeentnahme-Adresse:"
const val COST_LOCATION = "Kostenstelle:"
const val LAB_ID = "Labor-ID:"
const val CHOICE_OF_BASIS = "Wahl der Untersuchungsgrundlagen"

const val COVERING_LETTER_BASIS_DATA = "Begleitschein-Basis-Daten"
const val COVERING_BASIS = "Begleitschein-Normen/Grundlagen"
const val COVERING_LETTER_SERIES = "Begleitschein-Serien"
const val ADD_COVERING_BASIS = "Norm/Grundlage hinzufügen"
const val ADD_ADDRESS = "Adresse hinzufügen"
const val SAVE_ADDRESS = "Adresse speichern"
const val NEW_SAMPLE_LOCATION = "Neue Probeentnahmestelle"
const val SAMPLE_LOCATION = "Probeentnahmestelle"
const val DESCRIPTION = "Beschreibung"
const val EXTRA_INFO = "Zusätzliche Information"
const val NEXT_HEATER = "Nächste Heizung"
const val ADD_PARAMETER = "Parameter hinzufügen"

const val COVERING_LETTER = "Probenbegleitschein"
const val LAB_IN_DATE = "Laboreingang:"
const val RESULT_CREATED_DATE = "Befundet am"
const val LOT_ID = "Chargen-Nr.:"

const val CREATE_ADDITIONAL_COVERINGS = "Nachuntersuchungen erstellen"
const val SORT_BY = "Sortieren nach"

const val GIVE_BACK_COVERING_LETTER = "Probeentnahme zurückgeben"
const val END_REPORT = "Probeentnahme befunden"
const val HAND_IN_COVERING_LETTER = "Begleitschein im Labor abgeben"
const val SAVE = "Speichern"
const val LAB_REPORT_AVAILABLE = "Laborergebnisse vorhanden"

const val SAMPLE_ID = "Probennummer"
const val EXTRA_INFO_SAMPLING_PERSON = "Zusatzinfo der probenentnehmenden Person"
const val EXTRA_INFO_LAB_PERSON = "Zusatzinfo der Laborarbeiter:in"
const val SAMPLING_DATE = "Datum der Probeentnahme"
const val WARNING = "Warnung"

//Register/Login
const val LOGIN = "Login"
const val AS_HYGIENE_WORKER_REGISTER = "als Hygienearbeiter:in registrieren"
const val AS_SAMPLER_REGISTER = "als Probeentnehmer:in registrieren"
const val AS_LAB_WORKER_REGISTER = "als Laborarbeiter:in registrieren"

//communication UI
const val LOADING = "Lädt gerade"
const val NOT_LOADED = "nicht geladen"
const val ERROR = "Fehler"
const val SUCCESS = "Aktion war erfolgreich"
const val ACCEPT = "Ok"
const val CANCEL = "Abbrechen"
const val DELETE = "Löschen"

//firebase
const val SAMPLE_LOCATIONS_FIRESTORE = "sample_locations"
const val ADDRESSES_FIRESTORE = "addresses"
const val BASES_FIRESTORE = "bases"
const val USERS_FIRESTORE = "user"
const val COVERING_LETTER_SERIES_FIRESTORE = "covering_letter_series"
const val COVERING_LETTERS_FIRESTORE = "covering_letters"
const val ADDRESS_ID = "addressId"
const val SAMPLING_STATE_FIELD = "samplingState"
const val COVERING_LETTERS_ARRAY = "coveringLetters"

//time
const val WEEK_DAYS = 7
const val MONTH_DAYS = 30
const val QUARTER_YEAR_DAYS = 90
const val HALF_YEAR_DAYS = 180
const val YEAR_DAYS = 360

//data store
const val USER_TYPE = "userType"
