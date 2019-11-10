package es.uniovi.ips.hospital.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "codes")
public class ICD10 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "categoryCode")
    private String categoryCode;

    @Column(name = "diagnosisCode")
    private String diagnosisCode;

    @Column(name = "fullCode")
    private String fullCode;

    @Column(name = "abbreviatedDescription")
    private String abbreviatedDescription;

    @Column(name = "fullDescription")
    private String fullDescription;

    @Column(name = "categoryTitle")
    private String categoryTitle;

    @ManyToMany
    private Set<Diagnostic> diagnostics;

    public ICD10() {
        // Nothing here.
    }

    public ICD10(String categoryCode, String diagnosisCode, String fullCode, String abbreviatedDescription, String fullDescription, String categoryTitle) {
        super();
        this.categoryCode = categoryCode;
        this.diagnosisCode = diagnosisCode;
        this.fullCode = fullCode;
        this.abbreviatedDescription = abbreviatedDescription;
        this.fullDescription = fullDescription;
        this.categoryTitle = categoryTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getFullCode() {
        return fullCode;
    }

    public void setFullCode(String fullCode) {
        this.fullCode = fullCode;
    }

    public String getAbbreviatedDescription() {
        return abbreviatedDescription;
    }

    public void setAbbreviatedDescription(String abbreviatedDescription) {
        this.abbreviatedDescription = abbreviatedDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Set<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(Set<Diagnostic> diagnostics) {
        this.diagnostics = diagnostics;
    }

    @Override
    public String toString() {
        return "ICD10{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", diagnosisCode='" + diagnosisCode + '\'' +
                ", fullCode='" + fullCode + '\'' +
                ", abbreviatedDescription='" + abbreviatedDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                '}';
    }
}
