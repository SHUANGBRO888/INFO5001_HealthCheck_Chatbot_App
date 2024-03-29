package PatientManagement.Patient.Encounters;

import java.util.ArrayList;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Patient.Patient;
public class VitalSigns {
    Patient patient;
    Encounter encounter;
    ArrayList<VitalSignMetric> vitalSigns;

    public VitalSigns(Encounter e) {
        encounter = e;
        vitalSigns = new ArrayList<VitalSignMetric>();
    }

    public VitalSignMetric addNewVitals(String name, int value) {
        Patient patient = encounter.getEncounterHistory().getPatient();
        int age = patient.getPerson().getAge();
        Limits limits = encounter.getVitalSignLimits(age, name);
        if (limits == null)
            return null;
        VitalSignMetric newVitals = new VitalSignMetric(patient, name, limits, value);
        vitalSigns.add(newVitals);
        return newVitals;
    }

    public Boolean areNormal() {
        boolean normal = true;
        for (VitalSignMetric vsm : vitalSigns) {
            if (!vsm.isNormal())
                normal = false;
        }

        return normal;
    }

}
