<template>
    <v-container>
        <h1>Sign Up</h1>
        <div class="register">
            <input type="text" placeholder="Enter Name" />
            <input type="text" placeholder="Enter Email" />
            <input type="password" placeholder="Enter Password" />
            <button> Sign Up</button>
        </div>
    </v-container>
    
</template>

<script>
export default {
    name: 'SingUp'
}
</script>

<style>
.register input{
    width: 300px;
    height: 400px;
    padding-left: 20px;
    display: block;
    margin-right: auto;
    margin-left: auto;
    margin-bottom: 30px;
    border: 1px solid black;
}
</style>
