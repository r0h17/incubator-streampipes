package org.streampipes.model.util;

import org.streampipes.model.impl.JmsTransportProtocol;
import org.streampipes.model.impl.KafkaTransportProtocol;
import org.streampipes.model.impl.TransportFormat;
import org.streampipes.model.impl.eventproperty.EventProperty;
import org.streampipes.model.impl.eventproperty.EventPropertyList;
import org.streampipes.model.impl.eventproperty.EventPropertyNested;
import org.streampipes.model.impl.eventproperty.EventPropertyPrimitive;
import org.streampipes.model.impl.output.AppendOutputStrategy;
import org.streampipes.model.impl.output.CustomOutputStrategy;
import org.streampipes.model.impl.output.ListOutputStrategy;
import org.streampipes.model.impl.output.OutputStrategy;
import org.streampipes.model.impl.output.RenameOutputStrategy;
import org.streampipes.model.impl.output.ReplaceOutputStrategy;
import org.streampipes.model.impl.output.UriPropertyMapping;
import org.streampipes.model.impl.quality.EventPropertyQualityDefinition;
import org.streampipes.model.impl.quality.EventPropertyQualityRequirement;
import org.streampipes.model.impl.quality.MeasurementCapability;
import org.streampipes.model.impl.quality.MeasurementObject;
import org.streampipes.model.impl.ApplicationLink;
import org.streampipes.model.impl.EventStream;
import org.streampipes.model.impl.TransportProtocol;
import org.streampipes.model.impl.output.FixedOutputStrategy;
import org.streampipes.model.impl.staticproperty.AnyStaticProperty;
import org.streampipes.model.impl.staticproperty.CollectionStaticProperty;
import org.streampipes.model.impl.staticproperty.DomainStaticProperty;
import org.streampipes.model.impl.staticproperty.FreeTextStaticProperty;
import org.streampipes.model.impl.staticproperty.MappingPropertyNary;
import org.streampipes.model.impl.staticproperty.MappingPropertyUnary;
import org.streampipes.model.impl.staticproperty.MatchingStaticProperty;
import org.streampipes.model.impl.staticproperty.OneOfStaticProperty;
import org.streampipes.model.impl.staticproperty.Option;
import org.streampipes.model.impl.staticproperty.RemoteOneOfStaticProperty;
import org.streampipes.model.impl.staticproperty.StaticProperty;
import org.streampipes.model.impl.staticproperty.SupportedProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cloner {

	public OutputStrategy outputStrategy(OutputStrategy other)
	{
		if (other instanceof RenameOutputStrategy) return new RenameOutputStrategy((RenameOutputStrategy) other);
		else if (other instanceof FixedOutputStrategy) return new FixedOutputStrategy((FixedOutputStrategy) other);
		else if (other instanceof ListOutputStrategy) return new ListOutputStrategy((ListOutputStrategy) other);
		else if (other instanceof CustomOutputStrategy) return new CustomOutputStrategy((CustomOutputStrategy) other);
		else if (other instanceof ReplaceOutputStrategy) return new ReplaceOutputStrategy((ReplaceOutputStrategy) other);
		else return new AppendOutputStrategy((AppendOutputStrategy) other);
	}

	public StaticProperty staticProperty(StaticProperty o) {
		if (o instanceof FreeTextStaticProperty) return new FreeTextStaticProperty((FreeTextStaticProperty) o);
		else if (o instanceof OneOfStaticProperty) return new OneOfStaticProperty((OneOfStaticProperty) o);
		else if (o instanceof RemoteOneOfStaticProperty) return new RemoteOneOfStaticProperty((RemoteOneOfStaticProperty) o);
		else if (o instanceof MappingPropertyNary) return new MappingPropertyNary((MappingPropertyNary) o);
		else if (o instanceof DomainStaticProperty) return new DomainStaticProperty((DomainStaticProperty) o);
		else if (o instanceof AnyStaticProperty) return new AnyStaticProperty((AnyStaticProperty) o);
		else if (o instanceof CollectionStaticProperty) return new CollectionStaticProperty((CollectionStaticProperty) o);
		else if (o instanceof MatchingStaticProperty) return new MatchingStaticProperty((MatchingStaticProperty) o);
		else return new MappingPropertyUnary((MappingPropertyUnary) o);
	}
	
	public List<TransportProtocol> protocols(List<TransportProtocol> protocols)
	{
		return protocols.stream().map(o -> protocol(o)).collect(Collectors.toList());
	}
	
	public TransportProtocol protocol(TransportProtocol protocol) {
		if (protocol instanceof KafkaTransportProtocol) return new KafkaTransportProtocol((KafkaTransportProtocol) protocol);
		else return new JmsTransportProtocol((JmsTransportProtocol) protocol);
	}
	
	public EventProperty property(EventProperty o) {
		if (o instanceof EventPropertyPrimitive) return new EventPropertyPrimitive((EventPropertyPrimitive) o);
		else if (o instanceof EventPropertyList) return new EventPropertyList((EventPropertyList) o);
		else return new EventPropertyNested((EventPropertyNested) o);
	}

	public EventPropertyQualityRequirement qualityreq(EventPropertyQualityRequirement o) {
		// TODO Auto-generated method stub
		return null;
	}

	public EventPropertyQualityDefinition qualitydef(EventPropertyQualityDefinition o) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EventStream> streams(List<EventStream> eventStreams) {
		return eventStreams.stream().map(s -> new EventStream(s)).collect(Collectors.toList());
	}
	
	public EventStream stream(EventStream other) {
		return new EventStream(other);
	}

	public List<OutputStrategy> strategies(List<OutputStrategy> outputStrategies) {
		if (outputStrategies != null) return outputStrategies.stream().map(o -> outputStrategy(o)).collect(Collectors.toList());
		else return new ArrayList<OutputStrategy>();
	}

	public List<StaticProperty> staticProperties(
			List<StaticProperty> staticProperties) {
		return staticProperties.stream().map(o -> staticProperty(o)).collect(Collectors.toList());
	}

	public List<TransportFormat> transportFormats(
			List<TransportFormat> transportFormats) {
		return transportFormats.stream().map(t -> new TransportFormat(t)).collect(Collectors.toList());
	}

	public List<EventProperty> properties(List<EventProperty> eventProperties) {
		return eventProperties.stream().map(o -> new Cloner().property(o)).collect(Collectors.toList());
	}

	public List<EventPropertyQualityRequirement> reqEpQualitities(
			List<EventPropertyQualityRequirement> requiresEventPropertyQualities) {
		return requiresEventPropertyQualities.stream().map(o -> new Cloner().qualityreq(o)).collect(Collectors.toList());
	}

	public List<EventPropertyQualityDefinition> provEpQualities(
			List<EventPropertyQualityDefinition> eventPropertyQualities) {
		return eventPropertyQualities.stream().map(o -> new Cloner().qualitydef(o)).collect(Collectors.toList());
	}

	public List<Option> options(List<Option> options) {
		return options.stream().map(o -> new Option(o)).collect(Collectors.toList());
	}

	public List<SupportedProperty> supportedProperties(
			List<SupportedProperty> supportedProperties) {
		return supportedProperties.stream().map(s -> new SupportedProperty(s)).collect(Collectors.toList());
	}

	public List<String> epaTypes(List<String> epaTypes) {
		return epaTypes;
	}

	public List<String> ecTypes(List<String> ecTypes) {
		return ecTypes;
	}

	public List<UriPropertyMapping> replaceStrategy(
			List<UriPropertyMapping> replaceProperties) {
		return replaceProperties.stream().map(s -> new UriPropertyMapping(s)).collect(Collectors.toList());
	}

	public List<MeasurementCapability> mc(
			List<MeasurementCapability> measurementCapability) {
		return measurementCapability.stream().map(m -> new MeasurementCapability(m)).collect(Collectors.toList());
	}

	public List<MeasurementObject> mo(List<MeasurementObject> measurementObject) {
		return measurementObject.stream().map(m -> new MeasurementObject(m)).collect(Collectors.toList());
	}

	public List<ApplicationLink> al(List<ApplicationLink> applicationLinks) {
		return applicationLinks.stream().map(m -> new ApplicationLink(m)).collect(Collectors.toList());
	}
	
}