package tech.ippon.generated;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("tech.ippon.generated");

        noClasses()
            .that()
                .resideInAnyPackage("tech.ippon.generated.service..")
            .or()
                .resideInAnyPackage("tech.ippon.generated.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..tech.ippon.generated.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
