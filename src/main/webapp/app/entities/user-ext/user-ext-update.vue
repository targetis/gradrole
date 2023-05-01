<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="gradroleApp.userExt.home.createOrEditLabel"
          data-cy="UserExtCreateUpdateHeading"
          v-text="$t('gradroleApp.userExt.home.createOrEditLabel')"
        >
          Create or edit a UserExt
        </h2>
        <div>
          <div class="form-group" v-if="userExt.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="userExt.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.userExt.middleName')" for="user-ext-middleName">Middle Name</label>
            <input
              type="text"
              class="form-control"
              name="middleName"
              id="user-ext-middleName"
              data-cy="middleName"
              :class="{ valid: !$v.userExt.middleName.$invalid, invalid: $v.userExt.middleName.$invalid }"
              v-model="$v.userExt.middleName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.userExt.jobRole')" for="user-ext-jobRole">Job Role</label>
            <input
              type="text"
              class="form-control"
              name="jobRole"
              id="user-ext-jobRole"
              data-cy="jobRole"
              :class="{ valid: !$v.userExt.jobRole.$invalid, invalid: $v.userExt.jobRole.$invalid }"
              v-model="$v.userExt.jobRole.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.userExt.dateOfBirth')" for="user-ext-dateOfBirth"
              >Date Of Birth</label
            >
            <input
              type="text"
              class="form-control"
              name="dateOfBirth"
              id="user-ext-dateOfBirth"
              data-cy="dateOfBirth"
              :class="{ valid: !$v.userExt.dateOfBirth.$invalid, invalid: $v.userExt.dateOfBirth.$invalid }"
              v-model="$v.userExt.dateOfBirth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('gradroleApp.userExt.user')" for="user-ext-user">User</label>
            <select class="form-control" id="user-ext-user" data-cy="user" name="user" v-model="userExt.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="userExt.user && userOption.id === userExt.user.id ? userExt.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.login }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.userExt.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./user-ext-update.component.ts"></script>
