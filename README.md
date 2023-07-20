# peppol-reporting

[![Maven Central](https://img.shields.io/maven-central/v/com.helger/peppol-reporting.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.helger%22%20AND%20a%3A%22peppol-reporting%22)

Peppol Reporting support library.
Peppol Reporting is the process of collecting, aggregating and transmitting Peppol Reports to OpenPeppol. 

This library supports the following reports:
* Peppol Transaction Statistics Report 1.0.1 (March 2023)
    * Supersedes 1.0.0 ([November 2022](https://openpeppol.atlassian.net/wiki/spaces/RR/pages/2967863297/End+user+statistics+reporting+BIS+22+November+2022))
* Peppol End User Statistics Report 1.1.0 (June 2023) 
    * Supersedes 1.0.0 and 1.0.0-RC2 ([November 2022](https://openpeppol.atlassian.net/wiki/spaces/RR/pages/2967863297/End+user+statistics+reporting+BIS+22+November+2022))

This library does not deal with the transmission of Reports.
That needs to be done with [phase4](https://github.com/phax/phase4) or another AS4 solution.

This library requires Java 11 and Maven to build.

# How to use it

This library offers a Java domain model for EUSR and TSR reports.

## Data collection

The data for reporting needs to be collected in instances of class `PeppolReportingItem`.
For each sent or received Peppol transmission, such a `PeppolReportingItem` needs to be collected, and persisted. 

## Data aggregation

To aggregate data for a single Reporting Period, all the matching `PeppolReportingItem` objects need to be collected first.
All the matching items need to be fed into the respective report builder.

Via the builder `EndUserStatisticsReport.builder ()`, the report of type `EndUserStatisticsReportType` can be created.

Via the builder `TransactionStatisticsReport.builder ()`, the report of type `TransactionStatisticsReportType` can be created.

## XML Serialization

The JAXB generated domain model classes reside in the packages `com.helger.peppol.reporting.jaxb.eusr.v110` and `com.helger.peppol.reporting.jaxb.tsr.v101`.
This domain model can be read from and written to XML documents via the marshaller classes `EndUserStatisticsReport110Marshaller` and `TransactionStatisticsReport101Marshaller`.

## Validation

Additionally, the Schematron compatibility can be verified using the classes `EndUserStatisticsReportValidator` and `TransactionStatisticsReportValidator`.

# Glossary

* EUSR - End User Statistics Report
* TSR - Transaction Statistics Report
* Report - Document containing OpenPeppol reporting information
* Reporting - The process of transmitting a **Report** to OpenPeppol
* Reporting Period - The period for which reporting data is to be collected and transmitted to OpenPeppol 

# Maven usage

Add the following to your pom.xml to use this artifact, replacing `x.y.z` with the real version:

```xml
<dependency>
  <groupId>com.helger</groupId>
  <artifactId>peppol-reporting</artifactId>
  <version>x.y.z</version>
</dependency>
```

# News and Noteworthy

* v1.2.0 - 2023-07-20
    * Added data models to easily build End User Statistics Reports v1.1.0 in code
    * Added data models to easily build Transaction Statistics Reports v1.0.1 in code
* v1.1.0 - 2023-07-02
    * Updated to support EUSR 1.1.0
* v1.0.0 - 2023-04-26
    * Initial Version
    * Supports EUSR 1.0.0 and TSR 1.0.1 

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
It is appreciated if you star the GitHub project if you like it.