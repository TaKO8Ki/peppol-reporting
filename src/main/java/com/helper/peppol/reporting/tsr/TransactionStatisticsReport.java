package com.helper.peppol.reporting.tsr;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.builder.IBuilder;
import com.helger.commons.datetime.OffsetDate;
import com.helger.commons.datetime.XMLOffsetDate;
import com.helger.commons.log.ConditionalLogger;
import com.helger.commons.math.MathHelper;
import com.helger.commons.string.StringHelper;
import com.helger.peppol.reporting.jaxb.tsr.v101.HeaderType;
import com.helger.peppol.reporting.jaxb.tsr.v101.IncomingOutgoingType;
import com.helger.peppol.reporting.jaxb.tsr.v101.ReportPeriodType;
import com.helger.peppol.reporting.jaxb.tsr.v101.TransactionStatisticsReportType;
import com.helper.peppol.reporting.model.CPeppolReporting;
import com.helper.peppol.reporting.model.PeppolReportingItem;
import com.helper.peppol.reporting.tsr.model.CTSR;
import com.helper.peppol.reporting.tsr.model.TSRReportingItemList;

/**
 * Builder for Peppol Transaction Statistics Report objects.
 *
 * @author Philip Helger
 * @since 1.2.0
 */
@Immutable
public final class TransactionStatisticsReport
{
  private TransactionStatisticsReport ()
  {}

  /**
   * @return A new builder for TSR 1.9 reports and never <code>null</code>.
   */
  @Nonnull
  public static Builder10 builder ()
  {
    return new Builder10 ();
  }

  /**
   * The main builder class for Peppol Transaction Statistics Reports v1.0.
   *
   * @author Philip Helger
   * @since 1.2.0
   */
  public static class Builder10 implements IBuilder <TransactionStatisticsReportType>
  {
    private static final Logger LOGGER = LoggerFactory.getLogger (TransactionStatisticsReport.Builder10.class);

    private String m_sCustomizationID;
    private String m_sProfileID;
    private LocalDate m_aStartDate;
    private LocalDate m_aEndDate;
    private String m_sReportingServiceProviderIDScheme;
    private String m_sReportingServiceProviderID;
    private TSRReportingItemList m_aList;

    /**
     * Constructor. Sets default values for: {@link #customizationID(String)},
     * {@link #profileID(String)} and
     * {@link #reportingServiceProviderIDScheme(String)}
     */
    public Builder10 ()
    {
      customizationID (CTSR.CUSTOMIZATION_ID_V10);
      profileID (CTSR.PROFILE_ID_V10);
      reportingServiceProviderIDScheme (CPeppolReporting.SERVICE_PROVIDER_ID_SCHEME);
    }

    /**
     * Set the customization ID to be used. Defaulted in the constructor.
     *
     * @param s
     *        New value. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder10 customizationID (@Nullable final String s)
    {
      m_sCustomizationID = s;
      return this;
    }

    /**
     * Set the profile ID to be used. Defaulted in the constructor.
     *
     * @param s
     *        New value. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder10 profileID (@Nullable final String s)
    {
      m_sProfileID = s;
      return this;
    }

    /**
     * Set the reporting start date.
     *
     * @param a
     *        New value. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder10 startDate (@Nullable final LocalDate a)
    {
      m_aStartDate = a;
      return this;
    }

    /**
     * Set the reporting end date.
     *
     * @param a
     *        New value. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder10 endDate (@Nullable final LocalDate a)
    {
      m_aEndDate = a;
      return this;
    }

    /**
     * Set the reporting start and end date.
     *
     * @param a
     *        The date from which the first day of the month and the last day of
     *        the month are taken. May be <code>null</code>.
     * @return this for chaining
     * @see #startDate(LocalDate)
     * @see #endDate(LocalDate)
     */
    @Nonnull
    public Builder10 monthOf (@Nullable final LocalDate a)
    {
      return startDate (a == null ? null : a.withDayOfMonth (1)).endDate (a == null ? null
                                                                                    : a.plusMonths (1)
                                                                                       .withDayOfMonth (1)
                                                                                       .minusDays (1));
    }

    /**
     * Set the reporting start and end date.
     *
     * @param a
     *        The date from which the first day of the month and the last day of
     *        the month are taken. May be <code>null</code>.
     * @return this for chaining
     * @see #startDate(LocalDate)
     * @see #endDate(LocalDate)
     */
    @Nonnull
    public Builder10 monthOf (@Nullable final OffsetDate a)
    {
      return monthOf (a == null ? null : a.toLocalDate ());
    }

    /**
     * Set the reporting start and end date.
     *
     * @param a
     *        The date from which the first day of the month and the last day of
     *        the month are taken. May be <code>null</code>.
     * @return this for chaining
     * @see #startDate(LocalDate)
     * @see #endDate(LocalDate)
     */
    @Nonnull
    public Builder10 monthOf (@Nullable final OffsetDateTime a)
    {
      return monthOf (a == null ? null : a.toLocalDate ());
    }

    /**
     * Set the reporting start and end date.
     *
     * @param a
     *        The date from which the first day of the month and the last day of
     *        the month are taken. May be <code>null</code>.
     * @return this for chaining
     * @see #startDate(LocalDate)
     * @see #endDate(LocalDate)
     */
    @Nonnull
    public Builder10 monthOf (@Nullable final XMLOffsetDate a)
    {
      return monthOf (a == null ? null : a.toLocalDate ());
    }

    /**
     * Set the reporting Service Provider ID scheme to be used. Defaulted in the
     * constructor.
     *
     * @param s
     *        New value. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder10 reportingServiceProviderIDScheme (@Nullable final String s)
    {
      m_sReportingServiceProviderIDScheme = s;
      return this;
    }

    /**
     * Set the reporting Service Provider ID to be used. Usually has the layout
     * <code>P[A-Z]{2}[0-9]{6}</code>.
     *
     * @param s
     *        New value. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder10 reportingServiceProviderID (@Nullable final String s)
    {
      m_sReportingServiceProviderID = s;
      return this;
    }

    /**
     * Set the TSR reporting items based on which the report is to be created.
     *
     * @param a
     *        The list to be set. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder10 reportingItemList (@Nullable final TSRReportingItemList a)
    {
      m_aList = a;
      return this;
    }

    /**
     * Set the TSR reporting items based on which the report is to be created.
     *
     * @param aItems
     *        The items of which a new {@link TSRReportingItemList} is created
     *        and used. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder10 reportingItemList (@Nullable final PeppolReportingItem... aItems)
    {
      return reportingItemList (aItems == null ? null : new TSRReportingItemList (aItems));
    }

    /**
     * Set the TSR reporting items based on which the report is to be created.
     *
     * @param aItems
     *        The items of which a new {@link TSRReportingItemList} is created
     *        and used. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder10 reportingItemList (@Nullable final Iterable <? extends PeppolReportingItem> aItems)
    {
      return reportingItemList (aItems == null ? null : new TSRReportingItemList (aItems));
    }

    /**
     * Check if all mandatory fields are set or not.
     *
     * @param bLogFailures
     *        <code>true</code> if missing fields should be logged,
     *        <code>false</code> if not.
     * @return <code>true</code> if all mandatory fields are set,
     *         <code>false</code> if not.
     */
    public boolean isComplete (final boolean bLogFailures)
    {
      final ConditionalLogger aCondLogger = new ConditionalLogger (LOGGER, bLogFailures);
      if (StringHelper.hasNoText (m_sCustomizationID))
      {
        aCondLogger.warn ("CustomizationID is missing");
        return false;
      }

      if (StringHelper.hasNoText (m_sProfileID))
      {
        aCondLogger.warn ("ProfileID is missing");
        return false;
      }

      if (m_aStartDate == null)
      {
        aCondLogger.warn ("StartDate is missing");
        return false;
      }

      if (m_aEndDate == null)
      {
        aCondLogger.warn ("EndDate is missing");
        return false;
      }

      if (m_aEndDate.isBefore (m_aStartDate))
      {
        aCondLogger.warn ("StartDate must be before or equal to the EndDate");
        return false;
      }

      if (StringHelper.hasNoText (m_sReportingServiceProviderIDScheme))
      {
        aCondLogger.warn ("Reporting Service Provider ID Scheme is missing");
        return false;
      }

      if (StringHelper.hasNoText (m_sReportingServiceProviderID))
      {
        aCondLogger.warn ("Reporting Service Provider ID is missing");
        return false;
      }

      if (m_aList == null)
      {
        aCondLogger.warn ("Reporting Item list is missing");
        return false;
      }

      aCondLogger.trace ( () -> "Builder fields are complete");
      return true;
    }

    /**
     * Build the main TSR report. Use {@link #isComplete(boolean)} to check if
     * all mandatory fields are set or not.
     *
     * @see #isComplete(boolean)
     */
    @Nonnull
    public TransactionStatisticsReportType build ()
    {
      if (!isComplete (true))
        throw new IllegalStateException ("The TSR builder was not filled completely");

      final TransactionStatisticsReportType aReport = new TransactionStatisticsReportType ();
      aReport.setCustomizationID (m_sCustomizationID);
      aReport.setProfileID (m_sProfileID);

      {
        final HeaderType aHeader = new HeaderType ();
        final ReportPeriodType aPeriod = new ReportPeriodType ();
        aPeriod.setStartDate (XMLOffsetDate.of (m_aStartDate));
        aPeriod.setEndDate (XMLOffsetDate.of (m_aEndDate));
        aHeader.setReportPeriod (aPeriod);
        aHeader.setReporterID (m_sReportingServiceProviderID).setSchemeID (m_sReportingServiceProviderIDScheme);
        aReport.setHeader (aHeader);
      }

      {
        final IncomingOutgoingType aFullSet = new IncomingOutgoingType ();
        aFullSet.setIncoming (MathHelper.toBigInteger (m_aList.getTotalIncomingCount ()));
        aFullSet.setOutgoing (MathHelper.toBigInteger (m_aList.getTotalOutgoingCount ()));
        aReport.setTotal (aFullSet);
      }

      // Add all subsets
      m_aList.fillReportSubsets (aReport);
      return aReport;
    }
  }
}
