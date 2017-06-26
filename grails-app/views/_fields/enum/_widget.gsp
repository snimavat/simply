<g:if test="${inline}">
    <g:each in="${type.values()}" var="item">
        <label class="radio-inline">
            <g:radio name="${property}" value="${item.name()}" checked="${item == value }"/>&nbsp; ${item.name }
        </label>
    </g:each>
</g:if>

<g:else>
    <g:if test="${list}">
        <g:select
                name="${property}"
                from="${type.values()}"
                value="${value?.name()}"
                keys="${type.values()*.name() }"
                optionValue="name" noSelection="['':'--- Select ---']"
                class="form-control"
        />
    </g:if>

    <g:else>
        <g:each in="${type.values()}" var="item">
            <div class="radio">
            <label >
                <%-- <input type="checkbox" name="${property}" id="${property}" ${value ? 'checked' : '' }/>  --%>
                <g:radio name="${property}" value="${item.name()}" checked="${item == value }"/>&nbsp; ${item.name }
            </label>
            </div>
        </g:each>
    </g:else>

</g:else>