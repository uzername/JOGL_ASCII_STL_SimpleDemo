package STLparserSIMPLE.parsingResult;
//provides info about some metadata records encountered in file

public class ServiceSimpleParseResult extends BaseSimpleParseResult {
    public ServiceParsingResults recordType;

    public ServiceSimpleParseResult(ServiceParsingResults recordType) {
        this.recordType = recordType;
    }
    
}
