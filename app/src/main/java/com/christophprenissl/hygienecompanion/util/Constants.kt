package com.christophprenissl.hygienecompanion.util

import androidx.compose.ui.unit.dp

const val APP_TITLE = "Hygiene Companion"
const val APP_TITLE_START = "| $APP_TITLE"
const val UKR_LOGO_DESCRIPTION = "UKR Logo"
const val BACK_BUTTON_DESCRIPTION = "Zurück"

//permissions
const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34

//sizes
const val halfSize = 0.5f
val cardPadding = 4.dp
val standardPadding = 8.dp
val doubleStandardPadding = standardPadding.times(2)
val standardElevation = 4.dp
val iconSize = 64.dp
val basicBorderStroke = 1.dp
val titleIconSize = iconSize.times(1.25f)
val titleDistance = 40.dp
val iconBarOffset = iconSize.times(-halfSize)
const val dropDownMenuWidth = 0.92f
val parameterCreationItemSize = 90.dp

//excel
const val PARAMETER = "Parametername"
const val SAMPLES = "Proben"
const val SUCCESS_SAVING = "Erfolgreich: %s wurde gespeichert"
const val ERROR_SAVING = "Fehler: Datei konnte nicht gespeichert werden"
const val NAME = "Name"
const val SAMPLER = "Probeentnehmer:in"
const val LAB_WORKER = "Laborarbeiter:in"
const val IS_SAMPLER_OF_INSTITUTE = "QM-Probeentnehmer:in des Instituts "

//views
const val HOME_ROUTE = "home_route"
const val LOGGED_OUT_ROUTE = "logged_out_route"
const val COVERING_LETTERS_ROUTE = "covering_letter_route"
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

const val EMPTY = "---"

const val SAVE_LOCATION = "Entnahmestelle speichern"

const val CREATE_ADDRESS = "Erstelle eine Adresse"
const val CREATED = "Erstellt am"
const val TO_CLIENT = "An den Auftraggeber senden"
const val TO_COVERING_PROPERTY = "an die Abnehmestelle senden"

const val CLIENT_ADDRESS = "Auftraggeber-Adresse"
const val COVERING_COMPANY_ADDRESS = "Probeentnehmer-Firma"
const val SET_COVERING_COMPANY_ADDRESS = "Probeentnehmer-Firma festlegen"
const val COVERING_ADDRESS = "Probeentnahmeadresse"
const val SET_COVERING_ADDRESS = "Probeentnahmeadresse festlegen"
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
const val RESULT_TO_CLIENT = "Befund an Auftraggeber:"
const val RESULT_TO_COVERING_ADDRESS = "Befund an Abnahmeadresse:"
const val COST_LOCATION = "Kostenstelle:"
const val LAB_ID = "Labor-ID:"
const val CHOICE_OF_BASIS = "Wahl der Untersuchungsgrundlagen"
const val ADD_BASIS = "Basis hinzufügen"
const val SET_CLIENT_ADDRESS = "Client Adresse festlegen"

const val USER_NAME = "Name"
const val HAS_CERTIFICATE = "Probeentnehmer-Zertifikat"
const val QM_SAMPLER = "QM-Probennehmer:in"

const val COVERING_LETTER_BASIS_DATA = "Begleitschein-Basis-Daten"
const val COVERING_BASIS = "Begleitschein-Normen/Grundlagen"
const val COVERING_LETTER_SERIES = "Begleitschein-Serien"
const val COVERING_LETTER_SERIE = "Begleitschein-Serie"
const val ADD_COVERING_BASIS = "Norm/Grundlage hinzufügen"
const val ADD_ADDRESS = "Adresse hinzufügen"
const val SAVE_ADDRESS = "Adresse speichern"
const val NEW_SAMPLE_LOCATION = "Neue Probeentnahmestelle"
const val SAMPLE_LOCATION = "Probeentnahmestelle"
const val DESCRIPTION = "Beschreibung"
const val EXTRA_INFO = "Zusätzliche Information"
const val NEXT_HEATER = "Nächste Heizung"
const val ADD_PARAMETER = "Parameter hinzufügen"
const val ADD_COVERING_LETTER_SERIES = "Begleitscheinserie hinzufügen"

const val COVERING_LETTER = "Probenbegleitschein"
const val NEXT_COVERING_LETTERS = "Anstehende Probeentnahmen"
const val LAB_IN_DATE = "Laboreingang:"
const val RESULT_CREATED_DATE = "Befundet am"
const val LOT_ID = "Chargen-Nr.:"

const val CREATE_ADDITIONAL_COVERINGS = "Nachuntersuchungen erstellen"
const val SORT_BY = "Sortieren nach"

const val GIVE_BACK_COVERING_LETTER = "Probeentnahme zurückgeben"
const val END_REPORT = "Probeentnahme befunden"
const val HAND_IN_COVERING_LETTER = "Begleitschein im Labor abgeben"
const val SAVE = "Speichern"
const val SAVED = "Gespeichert"
const val SUCCESS_REJECT = "Begleitschein wurde erfolgreich zurückgewiesen"
const val LAB_REPORT_AVAILABLE = "Laborergebnisse vorhanden"
const val SUCCESS_REPORT = "Befund wurde erfolgreich erstellt"
const val SUCCESS_GIVE_TO_LAB = "Begleitschein erfolgreich im Labor abgegeben"

const val SAMPLE_ID = "Probennummer"
const val EXTRA_INFO_SAMPLING_PERSON = "Zusatzinfo der probenentnehmenden Person"
const val EXTRA_INFO_LAB_PERSON = "Zusatzinfo der Laborarbeiter:in"
const val SAMPLING_DATE = "Datum der Probeentnahme"
const val WARNING = "Warnung"

const val SAVE_AS_EXCEL = "Als Exceldatei exportieren"

//Register/Login
const val LOGIN = "Login"

//communication UI
const val LOADING = "Lädt gerade"
const val NOT_LOADED = "nicht geladen"
const val ERROR = "Fehler"
const val ACCEPT = "Ok"
const val CANCEL = "Abbrechen"
const val DELETE = "Löschen"

const val REPORTS = "Befunde"

const val TRUE = "Wahr"
const val FALSE = "Falsch"

//firebase
const val SAMPLE_LOCATIONS_FIRESTORE = "sample_locations"
const val ADDRESSES_FIRESTORE = "addresses"
const val BASES_FIRESTORE = "bases"
const val USERS_FIRESTORE = "user"
const val COVERING_LETTER_SERIES_FIRESTORE = "covering_letter_series"
const val COVERING_LETTERS_FIRESTORE = "covering_letters"
const val ADDRESS_ID = "addressId"
const val SAMPLING_STATE_FIELD = "samplingState"
const val HAS_ENDED_FIELD = "hasEnded"
const val COVERING_LETTERS_ARRAY = "coveringLetters"

//time
const val WEEK_DAYS = 7
const val MONTH_DAYS = 30
const val QUARTER_YEAR_DAYS = 90
const val HALF_YEAR_DAYS = 180
const val YEAR_DAYS = 360

//data store
const val USER_PREF_STORE = "user"
const val USER_NAME_PREF = "userName"
const val HAS_CERTIFICATE_PREF = "hasCertificate"
const val QM_PREF = "qm"
const val USER_TYPE_PREF = "userType"
const val NO_LAB_WORKER_REGISTERED = "Keine Laborarbeit:in registriert"
const val NO_SAMPLER_REGISTERED = "Keine Probeentnehmer:in registriert"
